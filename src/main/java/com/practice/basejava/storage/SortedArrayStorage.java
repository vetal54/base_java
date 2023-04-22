package com.practice.basejava.storage;

import com.practice.basejava.model.Resume;
import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

  @Override
  protected int getIndex(String uuid) {
    Resume searchKey = new Resume();
    searchKey.setUuid(uuid);
    return Arrays.binarySearch(storage, 0, size, searchKey);
  }

  @Override
  public void clear() {

  }

  @Override
  public void update(Resume r) {

  }

  @Override
  public void save(Resume r) {

  }

  @Override
  public void delete(String uuid) {

  }

  @Override
  public Resume[] getAll() {
    return new Resume[0];
  }
}
