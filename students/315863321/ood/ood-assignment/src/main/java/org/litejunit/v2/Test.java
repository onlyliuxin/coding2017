package org.litejunit.v2;


/**
 * Created by john on 2017/8/30.
 */

public interface Test {
    int countTestCases();

    void run(TestResult tr);
}
