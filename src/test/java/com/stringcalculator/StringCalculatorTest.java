package com.stringcalculator;

import static org.junit.Assert.assertEquals;

import com.stringcalculator.utils.StringCalculator;
import com.stringcalculator.utils.interfaces.IStringCalculator;
import org.junit.Test;

/**
 * Unit test for StringCalculator App.
 */
public class StringCalculatorTest {

  @Test
  public void testEmptyStringReturnsZero() {
    IStringCalculator calculator = new StringCalculator();
    assertEquals(0, calculator.add(""));
  }

  @Test
  public void testSingleNumberReturnsItself() {
    IStringCalculator calculator = new StringCalculator();
    assertEquals(1, calculator.add("1"));
    assertEquals(2, calculator.add("2"));
  }

  @Test
  public void testTwoNumbersReturnsSum() {
    IStringCalculator calculator = new StringCalculator();
    assertEquals(3, calculator.add("1,2"));
  }

  @Test
  public void testMultipleNumbersReturnsSum() {
    IStringCalculator calculator = new StringCalculator();
    assertEquals(21, calculator.add("1,2,3,4,5,6"));
  }
}
