package com.practice;

public class ArrayStorage {

  private final Resume[] storage = new Resume[10000];
  private int index = 0;

  void clear() {
    for (int i = 0; i < index; i++) {
      storage[i] = null;
    }
    index = 0;
  }

  void save(Resume r) {
    storage[index++] = r;
  }

  Resume get(String uuid) {
    for (int i = 0; i < index; i++) {
      if (uuid.equals(storage[i].uuid)) {
        return storage[i];
      }
    }
    return null;
  }

  void delete(String uuid) {
    for (int i = 0; i < index; i++) {
      if (uuid.equals(storage[i].uuid)) {
        storage[i] = storage[index - 1];
        storage[index - i] = null;
        index--;
      }
    }
  }

  Resume[] getAll() {
    Resume[] resume = new Resume[index];
    for (int i = 0; i < index; i++) {
      resume[i] = storage[i];
    }
    return resume;
  }

  int size() {
    return index;
  }
}
