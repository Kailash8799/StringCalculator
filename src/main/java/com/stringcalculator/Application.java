package com.stringcalculator;

import com.stringcalculator.utils.StringCalculator;
import com.stringcalculator.utils.interfaces.IStringCalculator;

public class Application {

  public static void main(String[] args) {
    IStringCalculator calculator = new StringCalculator();

    // test case : 1 -> empty string
    int sum = calculator.add("");
    System.out.println("Sum of empty stirng is : " + sum);

    // test case : 2 -> string with 1 number
    sum = calculator.add("1");
    System.out.println("Sum of 1 number is : " + sum);

    // test case : 3 -> string with comma(,) seperated 2 numbers
    sum = calculator.add("1,2");
    System.out.println("Sum of 2 number is : " + sum);

    // test case : 4 -> string with comma seperated multiple numbers
    sum = calculator.add("1,2,3,4,5,6");
    System.out.println("Sum of 6 number is : " + sum);
  }
}
