package com.stringcalculator;

import static org.junit.Assert.assertEquals;

import com.stringcalculator.utils.StringCalculator;
import com.stringcalculator.utils.interfaces.IStringCalculator;
import org.junit.Test;

/**
 * Unit test for StringCalculator App.
 */
public class StringCalculatorTest {

  IStringCalculator calculator = new StringCalculator();

  @Test
  public void testEmptyStringReturnsZero() {
    assertEquals(0, calculator.add(""));
  }

  @Test
  public void testSingleNumberReturnsItself() {
    assertEquals(1, calculator.add("1"));
    assertEquals(2, calculator.add("2"));
  }

  @Test
  public void testTwoNumbersReturnsSum() {
    assertEquals(3, calculator.add("1,2"));
  }

  @Test
  public void testMultipleNumbersReturnsSum() {
    assertEquals(21, calculator.add("1,2,3,4,5,6"));
  }

  @Test
  public void testNewLinesBetweenNumbers() {
    assertEquals(21, calculator.add("1\n2,3,4,5\n6"));
  }

  @Test
  public void testDifferentDelimiters() {
    assertEquals(6, calculator.add("//;\n1;2;3"));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNegativeNumbersThrowException() {
    calculator.add("1,-2,3");
  }

  @Test
  public void testGetCalledCount() {
    calculator.add("1,2");
    assertEquals(1, calculator.getCalledCount());
    calculator.add("3,4");
    assertEquals(2, calculator.getCalledCount());
  }
}
