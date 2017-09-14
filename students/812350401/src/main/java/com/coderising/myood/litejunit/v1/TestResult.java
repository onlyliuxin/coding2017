package com.coderising.myood.litejunit.v1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thomas_young on 21/8/2017.
 */
public class TestResult {
    protected List<TestFailure> failures;  // 存储assert失败
    protected List<TestFailure> errors;  // 存储业务代码异常，比如空指针
    private int testCount;
    private boolean stop;

    public TestResult() {
        failures= new ArrayList<>();
        errors= new ArrayList<>();

        testCount= 0;
        stop= false;
    }

    public void run(TestCase testCase) {
        startTest(testCase);
        try {
            testCase.doRun();
        } catch (AssertionFailedError e) {
            failures.add(new TestFailure(testCase, e));
        } catch (Throwable e) {
            errors.add(new TestFailure(testCase, e));
        }
        endTest(testCase);
    }

    private void startTest(TestCase testCase) {
        int count= testCase.countTestCases();
        testCount+= count;
    }

    private void endTest(TestCase testCase) {
    }
    /**
     * Gets the number of run tests.
     */
    public  int runCount() {
        return testCount;
    }


    public  boolean shouldStop() {
        return stop;
    }

    public  void stop() {
        stop= true;
    }

    public  int errorCount() {
        return errors.size();
    }

    public Iterator errors() {
        return errors.iterator();
    }

    public  int failureCount() {
        return failures.size();
    }

    public  Iterator<TestFailure> failures() {
        return failures.iterator();
    }
    /**
     * Returns whether the entire test was successful or not.
     */
    public  boolean wasSuccessful() {
        return this.failureCount() == 0 && this.errorCount() == 0;
    }

    public void clearResult() {
        failures.clear();
        errors.clear();
        testCount = 0;
        stop= false;
    }

}
