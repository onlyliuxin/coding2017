package com.coderising.myood.litejunit.v2.extension;

import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestResult;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class RepeatedTest extends TestDecorate {
    private int repeatedTimes;
    public RepeatedTest(Test test, int repeatedTimes) {
        super(test);
        this.repeatedTimes = repeatedTimes;
    }
    @Override
    public void run(TestResult tr) {
        for (int i=0; i<repeatedTimes; i++) {
            if (!tr.shouldStop()) {
                super.run(tr);
            }
        }
    }
}
