package com.practice.basejava.storage;

import com.practice.basejava.model.Resume;

public class ArrayStorage implements Storage {

  private final Resume[] storage = new Resume[10000];
  private int size = 0;

  @Override
  public void clear() {
    for (int i = 0; i < size; i++) {
      storage[i] = null;
    }
    size = 0;
  }

  @Override
  public void save(Resume r) {
    if (getIndex(r.getUuid()) != -1) {
      System.out.println("Resume " + r.getUuid() + " already exist");
    } else if (size == storage.length) {
      System.out.println("Storage is full");
    } else {
      storage[size++] = r;
    }
  }

  @Override
  public void update(Resume r) {
    int index = getIndex(r.getUuid());
    if (index == -1) {
      System.out.println("Resume " + r.getUuid() + " not exist");
    } else {
      storage[index] = r;
    }
  }

  @Override
  public Resume get(String uuid) {
    int index = getIndex(uuid);
    if (index != -1) {
      return storage[index];
    }
    System.out.println("Resume " + uuid + " not exist");
    return null;
  }

  @Override
  public void delete(String uuid) {
    int index = getIndex(uuid);
    if (index != -1) {
      storage[index] = storage[size - 1];
      storage[size - 1] = null;
      size--;
    } else {
      System.out.println("Resume " + uuid + " not exist");
    }
  }

  @Override
  public Resume[] getAll() {
    Resume[] resume = new Resume[size];
    System.arraycopy(storage, 0, resume, 0, size);
    return resume;
  }

  @Override
  public int size() {
    return size;
  }

  private int getIndex(String uuid) {
    for (int i = 0; i < size; i++) {
      if (uuid.equals(storage[i].getUuid())) {
        return i;
      }
    }
    return -1;
  }
}
