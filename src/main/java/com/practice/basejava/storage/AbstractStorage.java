package com.practice.basejava.storage;

import com.practice.basejava.exception.ExistStorageException;
import com.practice.basejava.exception.NotExistStorageException;
import com.practice.basejava.model.Resume;

public abstract class AbstractStorage implements Storage {

  protected abstract Object getSearchKey(String uuid);

  protected abstract void doUpdate(Resume r, Object searchKey);

  protected abstract boolean isExist(Object searchKey);

  protected abstract void doSave(Resume r, Object searchKey);

  protected abstract Resume doGet(Object searchKey);

  protected abstract void doDelete(Object searchKey);

  @Override
  public void save(Resume r) {
    Object searchKey = getNotExistedSearchKey(r.getUuid());
    doSave(r, searchKey);
  }

  @Override
  public void update(Resume r) {
    Object searchKey = getExistedSearchKey(r.getUuid());
    doUpdate(r, searchKey);
  }

  @Override
  public Resume get(String uuid) {
    Object searchKey = getExistedSearchKey(uuid);
    return doGet(searchKey);
  }

  @Override
  public void delete(String uuid) {
    Object searchKey = getExistedSearchKey(uuid);
    doDelete(searchKey);
  }

  private Object getExistedSearchKey(String uuid) {
    Object searchKey = getSearchKey(uuid);
    if (!isExist(searchKey)) {
      throw new NotExistStorageException(uuid);
    }
    return searchKey;
  }


  private Object getNotExistedSearchKey(String uuid) {
    Object searchKey = getSearchKey(uuid);
    if (isExist(searchKey)) {
      throw new ExistStorageException(uuid);
    }
    return searchKey;
  }
}
