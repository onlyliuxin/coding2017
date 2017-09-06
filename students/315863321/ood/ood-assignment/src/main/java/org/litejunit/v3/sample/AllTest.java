package org.litejunit.v3.sample;


import org.litejunit.v3.runner.RunWith;
import org.litejunit.v3.runners.Suite;

/**
 * Created by john on 2017/9/2.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        CalculatorTest.class,
        PersonTest.class
})
public class AllTest {
//    public static Test suite() {
//
//        TestSuite suite = new TestSuite("All Test");
//        suite.addTest(CalculatorSuite.suite());
//        suite.addTestSuite(PersonTest.class);
//        return suite;
//
//    }


//    static class OverallTestSetup extends TestSetup {
//
//        public OverallTestSetup(Test test) {
//            super(test);
//
//        }
//        protected void setUp() throws Exception {
//            System.out.println("this is overall testsetup");
//        }
//        protected void tearDown() throws Exception {
//            System.out.println("this is overall teardown");
//        }
//
//    }
}