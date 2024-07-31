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
   * Default delimiter when custom delimeter not provided.
   */
  private static final String DEFAULT_DELIMITER = "[,\n]";
  private static final int MAX_NUMBER = 1000;

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

    String delimiter = parseDelimiter(numbers);
    numbers = extractNumbers(numbers);

    String[] numArray = numbers.split(delimiter, -1);
    int sum = calculateSum(numArray);
    notifyListeners(numbers, sum);
    return sum;
  }

  @Override
  public int getCalledCount() {
    return callCount;
  }

  @Override
  public void addAddOccurredListener(IAddOccurredListener listener) {
    listeners.add(listener);
  }

  private String parseDelimiter(String numbers) {
    if (numbers.startsWith("//")) {
      String[] parts = numbers.split("\n", 2);
      String delimiterPart = parts[0].substring(2);
      return buildDelimiterRegex(delimiterPart);
    }
    return DEFAULT_DELIMITER;
  }

  private String buildDelimiterRegex(String delimiterPart) {
    StringBuilder regexBuilder = new StringBuilder();
    delimiterPart = removeBrackets(delimiterPart);

    String[] delimiters = delimiterPart.split("\\]\\[");
    for (String d : delimiters) {
      regexBuilder.append("\\Q").append(d).append("\\E|");
    }

    // Remove last pipe character from regex
    if (regexBuilder.length() > 0) {
      regexBuilder.setLength(regexBuilder.length() - 1);
    }
    return regexBuilder.toString();
  }

  private String removeBrackets(String delimiterPart) {
    if (delimiterPart.startsWith("[") && delimiterPart.endsWith("]")) {
      return delimiterPart.substring(1, delimiterPart.length() - 1);
    }
    return delimiterPart;
  }

  private String extractNumbers(String numbers) {
    if (numbers.startsWith("//")) {
      return numbers.split("\n", 2)[1];
    }
    return numbers;
  }

  private int calculateSum(String[] numArray) {
    StringBuilder negatives = new StringBuilder();
    int sum = 0;

    for (String value : numArray) {
      validateNumber(value);
      int number = Integer.parseInt(value);
      if (number < 0) {
        if (negatives.length() > 0) {
          negatives.append(", ");
        }
        negatives.append(number);
      } else if (number <= MAX_NUMBER) {
        sum += number;
      }
    }

    if (negatives.length() > 0) {
      throw new IllegalArgumentException("Negatives not allowed: " + negatives);
    }
    return sum;
  }

  private void validateNumber(String value) {
    if (value.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Invalid input: empty values are not allowed"
      );
    }
  }

  private void notifyListeners(String input, int result) {
    for (IAddOccurredListener listener : listeners) {
      listener.onAddOccurred(input, result);
    }
  }
}
