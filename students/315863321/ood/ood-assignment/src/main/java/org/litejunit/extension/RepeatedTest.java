package org.litejunit.extension;

import org.litejunit.v2.Test;
import org.litejunit.v2.TestResult;

/**
 * Created by john on 2017/9/2.
 */
public class RepeatedTest extends TestDecorator {
    private int fTimesRepeat;

    public RepeatedTest(Test test, int repeat) {
        super(test);
        if (repeat < 0)
            throw new IllegalArgumentException("Repetition count must be > 0");
        fTimesRepeat = repeat;
    }

    public int countTestCases() {
        return super.countTestCases() * fTimesRepeat;
    }

    public void run(TestResult result) {
        for (int i = 0; i < fTimesRepeat; i++) {
            if (result.shouldStop())
                break;
            super.run(result);
        }
    }

    public String toString() {
        return super.toString() + "(repeated)";
    }
}
