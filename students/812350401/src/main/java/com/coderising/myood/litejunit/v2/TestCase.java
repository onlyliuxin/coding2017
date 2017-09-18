package com.coderising.myood.litejunit.v2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * Created by thomas_young on 21/8/2017.
 */
public abstract class TestCase extends Assert implements Test {
    private String name;
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

    public void doRun() throws Throwable {
        setUp();
        try{
            runTest();
        }
        finally{
            tearDown();
        }
    }

    private void runTest() throws Throwable {
        Method method = null;
        try {
            method = getClass().getMethod(name, null);
        } catch (NoSuchMethodException e) {
            fail("Method \""+name+"\" not found");
        }
        if (!Modifier.isPublic(method.getModifiers())) {
            fail("Method \""+name+"\" should be public");
        }
        try {
            method.invoke(this, null);
        } catch (IllegalAccessException e) {
            e.fillInStackTrace();
            throw e;
        } catch (InvocationTargetException e) {
            e.fillInStackTrace();
            throw e.getTargetException();
        }
    }

    protected void setUp() {
    }

    protected void tearDown() {
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "name='" + getClass().getName() + "." + name + '\'' +
                '}';
    }
}
