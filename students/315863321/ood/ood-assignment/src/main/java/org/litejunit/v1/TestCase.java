package org.litejunit.v1;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by john on 2017/8/30.
 */
public abstract class TestCase implements Test {
    private String name;

    TestCase() {
        name = null;
    }

    public TestCase(String name) {
        this.name = name;
    }

    @Override
    public int countTestCases() {
        return 1;
    }

    @Override
    public void run(TestResult tr) {
        tr.run(this);
    }

    protected void doRun() throws Throwable{
        setUp();
        try {
            runTest();
        } finally {
            tearDown();
        }
    }

    protected void runTest() throws Throwable {
        Method runMethod = getClass().getMethod(name, null);
        try {
            runMethod.invoke(this, new Class[0]);
        } catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        }
    }

    protected void setUp() {

    }

    protected void tearDown() {

    }
}
