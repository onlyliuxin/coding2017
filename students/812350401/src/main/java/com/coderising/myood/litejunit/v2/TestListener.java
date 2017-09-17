package com.coderising.myood.litejunit.v2;

/**
 * Created by thomas_young on 17/9/2017.
 */
public interface TestListener {
    void addError(Test test, Throwable t);
    void addFailure(Test test, Throwable t);
    void startTest(Test test);
    void endTest(Test test);
}
