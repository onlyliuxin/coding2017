package com.coderising.myood.litejunit.v2.example_test.b;

import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestSuite;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class BAllTest {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(HelloWorldTest.class);
        suite.addTestSuite(ByebyeTest.class);
        return suite;
    }
}
