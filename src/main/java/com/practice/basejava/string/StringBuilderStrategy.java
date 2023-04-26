package com.practice.basejava.string;

public class StringBuilderStrategy implements StringConcatenation {

  @Override
  public void concatenate(int n) {
    StringBuilder builder = new StringBuilder();
    for (int i = 0; i < n; i++) {
      builder.append("a");
    }
  }
}
