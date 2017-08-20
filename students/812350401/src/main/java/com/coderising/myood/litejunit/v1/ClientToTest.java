package com.coderising.myood.litejunit.v1;


/**
 * Created by thomas_young on 21/8/2017.
 */
public class ClientToTest {

    public static void main(String[] args) {
        TestResult tr = new TestResult();

        Test cs1 = new CalculatorTest("testAdd");
        test(cs1, tr);
        Test cs2 = new CalculatorTest("testSubtract");
        test(cs2, tr);
    }

    private static void test(Test test, TestResult tr) {
        test.run(tr);
        System.out.println(tr.wasSuccessful());
        for (TestFailure failure: tr.failures) {
            System.err.println(failure);
        }
        System.out.println("runCount=" + tr.runCount());
    }
}
