package com.coderising.myood.litejunit.v2.extension;

import com.coderising.myood.litejunit.v2.Assert;
import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestResult;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class TestDecorate extends Assert implements Test {
    private Test test;
    public TestDecorate(Test test) {
        this.test = test;
    }

    @Override
    public int countTestCases() {
        return test.countTestCases();
    }

    @Override
    public void run(TestResult tr) {
        test.run(tr);
    }
}
