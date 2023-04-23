package com.practice.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

import com.practice.basejava.exception.ExistStorageException;
import com.practice.basejava.exception.NotExistStorageException;
import com.practice.basejava.exception.StorageException;
import com.practice.basejava.model.Resume;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

abstract class AbstractArrayStorageTest {

  private final Storage storage;

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";

  protected AbstractArrayStorageTest(Storage storage) {
    this.storage = storage;
  }

  @BeforeEach
  public void setUp() {
    storage.clear();
    storage.save(new Resume(UUID_1));
    storage.save(new Resume(UUID_2));
    storage.save(new Resume(UUID_3));
  }

  @Test
  void size() {
    assertSize(3);
  }

  @Test
  void clear() {
    storage.clear();
    assertSize(0);
  }

  @Test
  void update() {
    Resume newResume = new Resume(UUID_1);
    storage.update(newResume);
    assertSame(newResume, storage.get(UUID_1));
  }

  @Test
  void updateNotExists() {
    assertThrows(NotExistStorageException.class, () -> storage.update(new Resume()));
  }

  @Test
  void getAll() {
    Resume[] all = storage.getAll();
    assertEquals(3, all.length);
    assertEquals(new Resume(UUID_1), all[0]);
    assertEquals(new Resume(UUID_2), all[1]);
    assertEquals(new Resume(UUID_3), all[2]);
  }

  @Test
  void save() {
    Resume resume = new Resume();
    storage.save(resume);
    assertSize(4);
    assertGet(resume);
  }

  @Test
  void saveExist() {
    assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
  }

  @Test
  void saveOverflow() {
    assertThrows(StorageException.class, () -> {
      storage.clear();
      for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
        storage.save(new Resume());
      }
    });
  }

  @Test
  void delete() {
    storage.delete(UUID_1);
    assertSize(2);
    assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
  }

  @Test
  void deleteNotExist() {
    assertThrows(NotExistStorageException.class, () -> storage.delete("delete"));
  }

  @Test
  void get() {
    assertGet(new Resume(UUID_1));
    assertGet(new Resume(UUID_2));
    assertGet(new Resume(UUID_3));
  }

  @Test
  void getNotExist() {
    assertThrows(NotExistStorageException.class, () -> storage.get("newResume"));
  }

  @Test
  void saveAlreadyExist() {
    assertThrows(ExistStorageException.class, () -> storage.save(new Resume(UUID_1)));
  }

  private void assertGet(Resume r) {
    assertEquals(r, storage.get(r.getUuid()));
  }

  private void assertSize(int size) {
    assertEquals(size, storage.size());
  }
}