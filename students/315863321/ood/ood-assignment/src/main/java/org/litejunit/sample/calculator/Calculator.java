package org.litejunit.sample.calculator;

/**
 * Created by john on 2017/8/29.
 */
public class Calculator {

    private int result = 0;

    public void add(int x) {
        result += x;
    }

    public void subtract(int x) {
        result -= x;
    }

    public int getResult() {
        return this.result;
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.add(10);
        calculator.subtract(5);
        System.out.println(calculator.getResult());
    }
}