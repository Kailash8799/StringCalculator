package com.stringcalculator.utils;

import com.stringcalculator.utils.interfaces.IAddOccurredListener;
import com.stringcalculator.utils.interfaces.IStringCalculator;
import java.util.*;

/**
 * This class represents a simple String Calculator that can perform
 * addition on a string of numbers with custom delimiters.
 */

public class StringCalculator implements IStringCalculator {

  /**
   * The number of times the add method has been called.
   */
  private int callCount = 0;

  /**
   * List of listeners for the add occurred event.
   */
  private final List<IAddOccurredListener> listeners = new ArrayList<>();

  /**
   * Adds the numbers in the given string.
   *
   * @param numbers A string containing numbers to be added. It can contain
   *                custom delimiters specified in the format "//[delimiter]\n" or * or \n.
   *                If the string is empty, it returns 0.
   * @return The sum of the numbers in the integer.
   * @throws IllegalArgumentException If any negative numbers are found in the input.
   */
  @Override
  public int add(String numbers) {
    callCount++;
    if (numbers.isEmpty()) {
      notifyListeners(numbers, 0);
      return 0;
    }

    String delimiter = "[,\n]"; // this is default delimiter
    if (numbers.startsWith("//")) {
      // split by newline to get delimiter and numbers
      String[] parts = numbers.split("\n", 2);
      String delimiterPart = parts[0].substring(2);

      // remove square brackets if present in multi-character delimiter
      if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
        delimiterPart = delimiterPart.substring(1, delimiterPart.length() - 1);
      }

      // split by square brackets to get multiple delimiters
      String[] delimiters = delimiterPart.split("\\]\\[", -1);

      StringBuffer regexBuilder = new StringBuffer();
      for (String d : delimiters) {
        regexBuilder.append("\\Q").append(d).append("\\E|");
      }

      // remove last pipe character from regex
      if (regexBuilder.length() > 0) {
        regexBuilder.setLength(regexBuilder.length() - 1);
      }

      // set delimiter and numbers
      delimiter = regexBuilder.toString();
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

    // throw exception if negatives are present
    if (negatives.length() > 0) {
      throw new IllegalArgumentException(
        "Negatives not allowed: " + negatives.toString()
      );
    }

    // notify listeners with input and result
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
