package com.coderising.myood.litejunit.v2;


import com.coderising.myood.litejunit.v2.example_test.AllTest;

/**
 * Created by thomas_young on 21/8/2017.
 */
public class ClientToTest {

    public static void main(String[] args) {
        TestResult tr = new TestResult();
        Test testAll = AllTest.suite();
        tryTest(testAll, tr);
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
