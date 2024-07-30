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

    // test case : 5 -> string with newline and comma seperated numbers
    sum = calculator.add("1\n2,3,4,5\n6");
    System.out.println("Sum of 6 number is with newline : " + sum);

    // // test case : 6 -> invalid string with newline and comma seperated numbers
    try {
      sum = calculator.add("1,\n");
      System.out.println("Sum of 6 number is with newline : " + sum);
    } catch (Exception e) {
      System.err.println(e.toString());
    }

    // test case : 7 -> string with different delimiter
    sum = calculator.add("//;\n1;2");
    System.out.println("Sum With different delimiter : " + sum);

    // test case : 8 -> string with negative numbers
    try {
      sum = calculator.add("1,-2,3,-4");
      System.out.println("Sum of 3 number is : " + sum);
    } catch (Exception e) {
      System.err.println(e.toString());
    }

    // test case : 9 -> get called count
    int count = calculator.getCalledCount();
    System.out.println("Called count : " + count);

    // test case : 10 -> ignore numbers greater than 1000
    sum = calculator.add("1,2,1001");
    System.out.println("Sum of 2 number is : " + sum);

    // test case : 11 -> string with multiple length delimiter
    sum = calculator.add("//[***]\n1***2***3");
    System.out.println("Sum of 3 number is : " + sum);

    // test case : 12 -> string with multiple delimiters
    sum = calculator.add("//[*][%]\n1*2%3");
    System.out.println("Sum of 3 number is : " + sum);

    // test case : 13 -> string with multiple length multiple delimiters
    sum = calculator.add("//[**][%%]\n1**2%%3");
    System.out.println("Sum of 3 number is : " + sum);
  }
}
