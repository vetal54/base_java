package com.practice.basejava.storage;

import com.practice.basejava.model.Resume;

public class ArrayStorage {

  private final Resume[] storage = new Resume[10000];
  private int index = 0;

  public void clear() {
    for (int i = 0; i < index; i++) {
      storage[i] = null;
    }
    index = 0;
  }

  public void save(Resume r) {
    storage[index++] = r;
  }

  public Resume get(String uuid) {
    for (int i = 0; i < index; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return storage[i];
      }
    }
    return null;
  }

  public void delete(String uuid) {
    for (int i = 0; i < index; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        storage[i] = storage[index - 1];
        storage[index - i] = null;
        index--;
      }
    }
  }

  public Resume[] getAll() {
    Resume[] resume = new Resume[index];
    for (int i = 0; i < index; i++) {
      resume[i] = storage[i];
    }
    return resume;
  }

  public int size() {
    return index;
  }
}
