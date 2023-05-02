package com.practice.basejava;

import com.practice.basejava.model.Resume;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MainCollection {

  private static final String UUID_1 = "uuid1";
  private static final Resume RESUME_1 = new Resume(UUID_1);
  private static final String UUID_2 = "uuid2";
  private static final Resume RESUME_2 = new Resume(UUID_2);
  private static final String UUID_3 = "uuid3";
  private static final Resume RESUME_3 = new Resume(UUID_3);
  private static final String UUID_4 = "uuid4";
  private static final Resume RESUME_4 = new Resume(UUID_4);

  public static void main(String[] args) {
    Collection<Resume> collection = new ArrayList<>();
    collection.add(RESUME_1);
    collection.add(RESUME_2);
    collection.add(RESUME_3);
    for (Resume r : collection) {
      System.out.println(r);
      if (Objects.equals(r.getUuid(), UUID_1)) {
        collection.remove(r);
      }
    }
    Iterator<Resume> iterator = collection.iterator();
    while (iterator.hasNext()) {
      Resume r = iterator.next();
      System.out.println(r);
      if (Objects.equals(r.getUuid(), UUID_1)) {
        iterator.remove();
      }
    }
    System.out.println(collection);
    Map<String, Resume> map = new HashMap<>();
    map.put(UUID_1, RESUME_1);
    map.put(UUID_2, RESUME_2);
    map.put(UUID_3, RESUME_3);

    for (String uuid : map.keySet()) {
      System.out.println(map.get(uuid));
    }

    for (Resume entry : map.values()) {
      System.out.println(entry);
    }

    List<Resume> resumes = Arrays.asList(RESUME_1, RESUME_2, RESUME_3);
    resumes.remove(1);
    System.out.println(resumes);
  }
}
