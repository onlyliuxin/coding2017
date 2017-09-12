package org.litejunit.sample.calculator;


import org.litejunit.v1.TestCase;
import org.litejunit.v1.TestResult;
import org.litejunit.v1.TestSuite;

import static org.litejunit.v1.Assert.assertEquals;

/**
 * Created by john on 2017/8/29.
 */
public class CalculatorTest extends TestCase {
    public CalculatorTest(String name) {
        super(name);

    }

    Calculator calculator = null;

    public void setUp() {
        calculator = new Calculator();
    }

    public void tearDown() {
        calculator = null;
    }

    public void testAdd() {

        calculator.add(10);
//        System.out.println("CalculatorTest.testAdd");
        assertEquals(5,calculator.getResult());
    }

    public void testSubtract() {
        calculator.add(10);
        calculator.subtract(5);
//        System.out.println("CalculatorTest.testSubtract");
//        throw new RuntimeException("this is a test");
        assertEquals(5,calculator.getResult());
    }

    public static void main(String[] args) {
//          CalculatorTest c1 = new CalculatorTest("testAdd");
//          CalculatorTest c2 = new CalculatorTest("testSubtract");
//          c1.run();
//          c2.run();
        TestResult tr = new TestResult();
        TestSuite ts = new TestSuite(CalculatorTest.class);
        ts.run(tr);

        System.out.println(tr.failures().next().thrownException());
    }
}