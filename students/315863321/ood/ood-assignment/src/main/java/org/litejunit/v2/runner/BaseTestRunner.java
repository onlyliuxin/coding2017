package org.litejunit.v2.runner;

/**
 * Created by john on 2017/9/2.
 */

import org.litejunit.v2.Test;
import org.litejunit.v2.TestListener;
import org.litejunit.v2.TestSuite;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.NumberFormat;

public abstract class BaseTestRunner implements TestListener {
    public static final String SUITE_METHODNAME = "suite";

    /**
     * Returns a filtered stack trace
     */
    public static String getFilteredTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        String trace = buffer.toString();
        return trace;
        //return BaseTestRunner.filterStack(trace);
    }

    public Test getTest(String suiteClassName) {
        if (suiteClassName.length() <= 0) {
            return null;
        }
        Class testClass = null;
        try {
            testClass = loadSuiteClass(suiteClassName);
        } catch (ClassNotFoundException e) {
            String clazz = e.getMessage();
            if (clazz == null)
                clazz = suiteClassName;
            runFailed("Class not found \"" + clazz + "\"");
            return null;
        } catch (Exception e) {
            runFailed("Error: " + e.toString());
            return null;
        }
        Method suiteMethod = null;
        try {
            suiteMethod = testClass.getMethod(SUITE_METHODNAME, new Class[0]);
        } catch (Exception e) {
            // try to extract a test suite automatically
            //clearStatus();
            return new TestSuite(testClass);
        }
        Test test = null;
        try {
            test = (Test) suiteMethod.invoke(null, new Class[0]); // static method
            if (test == null)
                return test;
        } catch (InvocationTargetException e) {
            runFailed("Failed to invoke suite():" + e.getTargetException().toString());
            return null;
        } catch (IllegalAccessException e) {
            runFailed("Failed to invoke suite():" + e.toString());
            return null;
        }

        //clearStatus();
        return test;
    }

    protected Class<?> loadSuiteClass(String suiteClassName) throws ClassNotFoundException {

        //TODO
        return Class.forName(suiteClassName);


        //return getLoader().load(suiteClassName);
    }

    protected abstract void runFailed(String message);

    public String elapsedTimeAsString(long runTime) {
        return NumberFormat.getInstance().format((double) runTime / 1000);
    }
}