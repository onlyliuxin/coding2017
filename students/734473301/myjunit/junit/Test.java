package com.jyz.myjunit.junit;

/**
 * Created by jyz on 2017/9/16.
 */
public interface Test {
    void run(TestResult tr);
    int countTestCases();
}
