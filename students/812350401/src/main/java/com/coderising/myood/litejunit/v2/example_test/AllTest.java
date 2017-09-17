package com.coderising.myood.litejunit.v2.example_test;

import com.coderising.myood.litejunit.v2.Test;
import com.coderising.myood.litejunit.v2.TestSuite;
import com.coderising.myood.litejunit.v2.example_test.a.AAllTest;
import com.coderising.myood.litejunit.v2.example_test.b.BAllTest;
import com.coderising.myood.litejunit.v2.extension.RepeatedTest;
import com.coderising.myood.litejunit.v2.extension.SetUpTest;

/**
 * Created by thomas_young on 17/9/2017.
 */
public class AllTest {
    public static Test suite() {
        TestSuite suite = new TestSuite();
        suite.addTest(new RepeatedTest(AAllTest.suite(), 2));  // 5 * 2个用例
        suite.addTest(BAllTest.suite());  //  2个用例
        return new SetUpTest(suite) {
            @Override
            protected void setUp() {
                System.out.printf("All start!\n");
            }

            @Override
            protected void tearDown() {
                System.out.printf("All end!");
            }
        };
    }
}
