package com.stringcalculator.utils;

import com.stringcalculator.utils.interfaces.IStringCalculator;

public class StringCalculator implements IStringCalculator {

  @Override
  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }

    String delimiter = "[,\n]"; // this is default delimiter
    if (numbers.startsWith("//")) {
      String[] parts = numbers.split("\n", 2);
      delimiter = parts[0].substring(2);
      numbers = parts[1];
    }

    String[] nums = numbers.split(delimiter, -1);
    int sum = 0;
    for (String value : nums) {
      if (value.trim().isEmpty()) {
        throw new IllegalArgumentException(
          "Invalid input: empty values are not allowed"
        );
      }
      sum += Integer.parseInt(value);
    }
    return sum;
  }
}
