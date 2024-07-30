package com.stringcalculator.utils;

import com.stringcalculator.utils.interfaces.IStringCalculator;

public class StringCalculator implements IStringCalculator {

  @Override
  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }
    String[] nums = numbers.split("[,\n]", -1);
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
