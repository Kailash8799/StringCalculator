package com.stringcalculator.utils;

import com.stringcalculator.utils.interfaces.IStringCalculator;

public class StringCalculator implements IStringCalculator {

  @Override
  public int add(String numbers) {
    if (numbers.isEmpty()) {
      return 0;
    }
    String[] nums = numbers.split(",");
    int sum = 0;
    for (String value : nums) {
      sum += Integer.parseInt(value);
    }
    return sum;
  }
}
