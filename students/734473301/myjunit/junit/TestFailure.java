package com.jyz.myjunit.junit;

/**
 * Created by jyz on 2017/9/16.
 */
public class TestFailure {
    protected Test failedTest;
    protected Throwable throwException;

    public TestFailure(Test failedTest, Throwable throwException) {
        this.failedTest = failedTest;
        this.throwException = throwException;
    }

    public Test getFailedTest() {
        return failedTest;
    }

    public Throwable getThrowException() {
        return throwException;
    }

    @Override
    public String toString() {
        return "TestFailure{" +
                "failedTest=" + failedTest +
                ", throwException=" + throwException +
                '}';
    }
}
