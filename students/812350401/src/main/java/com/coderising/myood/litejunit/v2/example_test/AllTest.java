package com.coderising.myood.litejunit.v2.example_test;

import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestSuite;
import com.coderising.myood.litejunit.v2.example_test.a.AAllTest;
import com.coderising.myood.litejunit.v2.example_test.b.BAllTest;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class AllTest {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(AAllTest.suite());
        suite.addTest(BAllTest.suite());
        return suite;
    }
}
