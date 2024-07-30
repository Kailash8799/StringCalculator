package com.stringcalculator.utils;

import com.stringcalculator.utils.interfaces.IAddOccurredListener;
import com.stringcalculator.utils.interfaces.IStringCalculator;
import java.util.*;

public class StringCalculator implements IStringCalculator {

  private int callCount = 0;
  private final List<IAddOccurredListener> listeners = new ArrayList<>();

  @Override
  public int add(String numbers) {
    callCount++;
    if (numbers.isEmpty()) {
      return 0;
    }

    String delimiter = "[,\n]"; // this is default delimiter
    if (numbers.startsWith("//")) {
      String[] parts = numbers.split("\n", 2);
      String delimiterPart = parts[0].substring(2);
      delimiter =
        delimiterPart
          .replaceAll("\\[", "")
          .replaceAll("\\]", "")
          .replaceAll("\\*", "\\\\*");
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
      } else if (number <= 1000) {
        sum += number;
      }
    }

    if (negatives.length() > 0) {
      throw new IllegalArgumentException(
        "Negatives not allowed: " + negatives.toString()
      );
    }

    notifyListeners(numbers, sum);

    return sum;
  }

  @Override
  public int getCalledCount() {
    return this.callCount;
  }

  @Override
  public void addAddOccurredListener(IAddOccurredListener listener) {
    listeners.add(listener);
  }

  private void notifyListeners(String input, int result) {
    for (IAddOccurredListener listener : listeners) {
      listener.onAddOccurred(input, result);
    }
  }
}
