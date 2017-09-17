package com.coderising.myood.litejunit.v2.extension;

import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestResult;

/**
 * Created by thomas_young on 17/9/2017.
 */
public abstract class SetUpTest extends TestDecorate {
    public SetUpTest(Test test) {
        super(test);
    }
    protected abstract void setUp();
    protected abstract void tearDown();
    public void run(TestResult tr) {
        setUp();
        super.run(tr);
        tearDown();
    }

}
