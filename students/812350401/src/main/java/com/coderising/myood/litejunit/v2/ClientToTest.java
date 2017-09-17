package com.coderising.myood.litejunit.v2;


/**
 * Created by thomas_young on 21/8/2017.
 */
public class ClientToTest {

    public static void main(String[] args) {
        TestResult tr = new TestResult();

//        Test cs1 = new CalculatorTest("testAdd");
//        tryTest(cs1, tr);
//        Test cs2 = new CalculatorTest("testSubtract");
//        tryTest(cs2, tr);

        System.out.println("---------------------------------");
        tr.clearResult();
        Test ts = new TestSuite("AllTest");
//        ((TestSuite)ts).addTest(new CalculatorTest("haha"));
        ((TestSuite)ts).addTest(new TestSuite(CalculatorTest.class));
        tryTest(ts, tr);

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
