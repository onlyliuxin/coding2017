package com.coderising.myood.litejunit.v1;


/**
 * Created by thomas_young on 21/8/2017.
 */
public class ClientToTest {

    public static void main(String[] args) {
        TestResult tr = new TestResult();

        Test cs1 = new CalculatorTest("testAdd");
        tryTest(cs1, tr);
//        Test cs2 = new CalculatorTest("testSubtract");
//        tryTest(cs2, tr);

//        System.out.println("---------------------------------");
//        tr.clearResult();
//        Test ts1 = new TestSuite("AllTest1");
//        ((TestSuite)ts).addTest(new CalculatorTest("haha"));
//        ((TestSuite)ts1).addTest(new TestSuite(CalculatorTest.class));
//        tryTest(ts1, tr);

        System.out.println("---------------------------------");
        tr.clearResult();
        Test ts2 = new TestSuite(CalculatorTest.class);
        tryTest(ts2, tr);

    }

    private static void tryTest(Test test, TestResult tr) {
        test.run(tr);
        System.out.println(tr.wasSuccessful());
        for (TestFailure failure: tr.failures) {
            System.err.println(failure);
        }
        System.out.println("runCount=" + tr.runCount());
    }
}
