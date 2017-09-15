package org.litejunit.v1;

/**
 * Created by john on 2017/9/2.
 */
public class TestFailure {

    protected Test failedTest;
    protected Throwable thrownException;

    /**
     * Constructs a TestFailure with the given test and exception.
     */
    public TestFailure(Test failedTest, Throwable thrownException) {
        this.failedTest= failedTest;
        this.thrownException= thrownException;
    }
    /**
     * Gets the failed test.
     */
    public Test failedTest() {
        return failedTest;
    }
    /**
     * Gets the thrown exception.
     */
    public Throwable thrownException() {
        return thrownException;
    }
    /**
     * Returns a short description of the failure.
     */
    public String toString() {
        StringBuffer buffer= new StringBuffer();
        buffer.append(failedTest+": "+thrownException.getMessage());
        return buffer.toString();
    }

}
