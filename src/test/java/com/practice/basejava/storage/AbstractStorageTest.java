package com.practice.basejava.storage;

import static org.junit.jupiter.api.Assertions.*;

import com.practice.basejava.exception.ExistStorageException;
import com.practice.basejava.exception.NotExistStorageException;
import com.practice.basejava.exception.StorageException;
import com.practice.basejava.model.Resume;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public abstract class AbstractStorageTest {

  protected final Storage storage;

  private static final String UUID_1 = "uuid1";
  private static final String UUID_2 = "uuid2";
  private static final String UUID_3 = "uuid3";
  private static final String UUID_4 = "uuid4";

  private static final Resume RESUME_1;
  private static final Resume RESUME_2;
  private static final Resume RESUME_3;
  private static final Resume RESUME_4;

  static {
    RESUME_1 = new Resume(UUID_1, "Name1");
    RESUME_2 = new Resume(UUID_2, "Name2");
    RESUME_3 = new Resume(UUID_3, "Name3");
    RESUME_4 = new Resume(UUID_4, "Name4");
  }
  protected AbstractStorageTest(Storage storage) {
    this.storage = storage;
  }

  @BeforeEach
  public void setUp() {
    storage.clear();
    storage.save(RESUME_1);
    storage.save(RESUME_2);
    storage.save(RESUME_3);
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
    Resume newResume = new Resume(UUID_1, "New Name");
    storage.update(newResume);
    assertSame(newResume, storage.get(UUID_1));
  }

  @Test
  void updateNotExists() {
    assertThrows(NotExistStorageException.class, () -> storage.update(RESUME_4));
  }

  @Test
  public void getAllSorted() throws Exception {
    List<Resume> list = storage.getAllSorted();
    assertEquals(3, list.size());
    assertEquals(list, Arrays.asList(RESUME_1, RESUME_2, RESUME_3));
  }

  @Test
  void save() {
    storage.save(RESUME_4);
    assertSize(4);
    assertGet(RESUME_4);
  }

  @Test
  void saveExist() {
    assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
  }

  @Test
  void delete() {
    storage.delete(UUID_1);
    assertSize(2);
    assertThrows(NotExistStorageException.class, () -> storage.get(UUID_1));
  }

  @Test
  void deleteNotExist() {
    assertThrows(NotExistStorageException.class, () -> storage.delete(UUID_4));
  }

  @Test
  void get() {
    assertGet(RESUME_1);
    assertGet(RESUME_2);
    assertGet(RESUME_3);
  }

  @Test
  void getNotExist() {
    assertThrows(NotExistStorageException.class, () -> storage.get(UUID_4));
  }

  @Test
  void saveAlreadyExist() {
    assertThrows(ExistStorageException.class, () -> storage.save(RESUME_1));
  }

  private void assertGet(Resume r) {
    assertEquals(r, storage.get(r.getUuid()));
  }

  private void assertSize(int size) {
    assertEquals(size, storage.size());
  }
}