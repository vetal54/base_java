package com.practice.basejava.string;

public class StringStrategy implements StringConcatenation {

  @Override
  public void concatenate(int n) {
    String result = "";
    for (int i = 0; i < n; i++) {
      result += "a";
    }
  }
}
