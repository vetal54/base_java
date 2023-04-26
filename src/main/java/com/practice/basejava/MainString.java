package com.practice.basejava;

import com.practice.basejava.string.StringConcatenation;
import com.practice.basejava.string.StringStrategy;
import com.practice.basejava.string.StringBufferStrategy;
import com.practice.basejava.string.StringBuilderStrategy;

public class MainString {

  private static final int REPEAT = 500_000;

  public static void main(String[] args) {
    StringConcatenation[] strategies = {
        new StringStrategy(),
        new StringBuilderStrategy(),
        new StringBufferStrategy()
    };

    for (StringConcatenation strategy : strategies) {
      long startTime = System.currentTimeMillis();
      strategy.concatenate(REPEAT);
      long endTime = System.currentTimeMillis();
      System.out.printf("%s: %d ms%n", strategy.getClass().getSimpleName(), endTime - startTime);
    }
  }
}
