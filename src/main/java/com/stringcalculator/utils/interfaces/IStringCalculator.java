package com.stringcalculator.utils.interfaces;

public interface IStringCalculator {
  int add(String numbers);
  int getCalledCount();
  void addAddOccurredListener(IAddOccurredListener listener);
}
