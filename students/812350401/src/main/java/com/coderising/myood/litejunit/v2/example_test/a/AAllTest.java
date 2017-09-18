package com.coderising.myood.litejunit.v2.example_test.a;


import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestSuite;
import com.coderising.myood.litejunit.v2.extension.SetUpTest;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class AAllTest {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTestSuite(CalculatorTest.class);
        return new SetUpTest(suite) {

            @Override
            protected void setUp() {
                System.out.println("AAll start!");
            }

            @Override
            protected void tearDown() {
                System.out.println("AAll end!");
            }
        };
    }
}
