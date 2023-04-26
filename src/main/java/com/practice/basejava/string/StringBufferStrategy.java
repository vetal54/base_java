package com.practice.basejava.string;

public class StringBufferStrategy implements StringConcatenation {

  @Override
  public void concatenate(int n) {
    StringBuffer buffer = new StringBuffer();
    for (int i = 0; i < n; i++) {
      buffer.append("a");
    }
  }
}
