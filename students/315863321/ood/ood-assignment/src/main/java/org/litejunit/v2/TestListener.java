package org.litejunit.v2;

/**
 * Created by john on 2017/9/2.
 *
 */
public interface TestListener {

    void addError(Test test, Throwable t);

    void addFailure(Test test, AssertionFailedError t);

    void endTest(Test test);

    void startTest(Test test);
}
