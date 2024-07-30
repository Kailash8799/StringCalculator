package com.stringcalculator.utils;

import com.stringcalculator.utils.interfaces.IStringCalculator;

public class StringCalculator implements IStringCalculator {

  private int callCount = 0;

  @Override
  public int add(String numbers) {
    callCount++;
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
    StringBuilder negatives = new StringBuilder();
    int sum = 0;
    for (String value : nums) {
      if (value.trim().isEmpty()) {
        throw new IllegalArgumentException(
          "Invalid input: empty values are not allowed"
        );
      }
      int number = Integer.parseInt(value);
      if (number < 0) {
        if (negatives.length() > 0) {
          negatives.append(", ");
        }
        negatives.append(number);
      } else {
        sum += number;
      }
    }

    if (negatives.length() > 0) {
      throw new IllegalArgumentException(
        "Negatives not allowed: " + negatives.toString()
      );
    }
    return sum;
  }

  @Override
  public int getCalledCount() {
    return this.callCount;
  }
}
