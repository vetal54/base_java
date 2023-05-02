package com.practice.basejava.storage;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.practice.basejava.exception.StorageException;
import com.practice.basejava.model.Resume;
import org.junit.jupiter.api.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

  public AbstractArrayStorageTest(Storage storage) {
    super(storage);
  }

  @Test
  void saveOverflow() {
    assertThrows(StorageException.class, () -> {
      storage.clear();
      for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT + 1; i++) {
        storage.save(new Resume("Storage"));
      }
    });
  }
}
