package com.coderising.myood.litejunit.v2;


import com.coderising.myood.litejunit.v2.example_test.AllTest;

/**
 * Created by thomas_young on 21/8/2017.
 */
public class TextRunner implements TestListener {

    public static void main(String[] args) {
        TextRunner textRunner = new TextRunner();
        TestResult tr = new TestResult();
        tr.addListener(textRunner);
        Test testAll = AllTest.suite();
        tryTest(testAll, tr);
    }

    private static void tryTest(Test test, TestResult tr) {
        test.run(tr);
        System.out.println("\nrunCount=" + tr.runCount());
    }

    @Override
    public void addError(Test test, Throwable t) {
        System.out.print("E");
    }

    @Override
    public void addFailure(Test test, Throwable t) {
        System.out.print("F");
    }

    @Override
    public void startTest(Test test) {
        System.out.print(".");
    }

    @Override
    public void endTest(Test test) {

    }
}
