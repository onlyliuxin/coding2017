package com.coderising.myood.litejunit.v2.example_test.a;


import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestSuite;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class AAllTest {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CalculatorTest.class);
        return suite;
    }
}
