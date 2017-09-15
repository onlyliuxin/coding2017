package org.litejunit.v1;


/**
 * Created by john on 2017/8/30.
 */

public interface Test {
    int countTestCases();

    void run(TestResult tr);
}
