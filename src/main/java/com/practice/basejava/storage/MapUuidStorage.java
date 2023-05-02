package com.practice.basejava.storage;

import com.practice.basejava.model.Resume;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapUuidStorage extends AbstractStorage {

  private final Map<String, Resume> map = new HashMap<>();

  @Override
  protected String getSearchKey(String uuid) {
    return map.get(uuid).toString();
  }

  @Override
  protected void doUpdate(Resume r, Object searchKey) {
    map.put(searchKey.toString(), r);
  }

  @Override
  protected boolean isExist(Object searchKey) {
    return map.containsKey(searchKey.toString());
  }

  @Override
  protected void doSave(Resume r, Object searchKey) {
    map.put(searchKey.toString(), r);
  }

  @Override
  protected Resume doGet(Object searchKey) {
    return map.get(searchKey.toString());
  }

  @Override
  protected void doDelete(Object searchKey) {
    map.remove(searchKey.toString());
  }

  @Override
  public void clear() {
    map.clear();
  }

  @Override
  public List<Resume> doCopyAll() {
    return Collections.emptyList();
  }

  @Override
  public int size() {
    return map.size();
  }
}
