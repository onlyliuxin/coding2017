package com.coderising.myood.litejunit.v2;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

/**
 * Created by thomas_young on 21/8/2017.
 */
public class TestRunner implements TestListener {
    private PrintStream writer = System.out;
    private static final String SUITE = "suite";

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        TestRunner testRunner = new TestRunner();
        TestResult tr = new TestResult();
        tr.addListener(testRunner);  // 添加listener
        String className = "com.coderising.myood.litejunit.v2.example_test.AllTest";
        Class caseClazz = Class.forName(className);
        Method suiteMethod = caseClazz.getMethod(SUITE, new Class[0]);
        Test suite = (Test) suiteMethod.invoke(null);
        testRunner.tryTest(suite, tr);
    }

    private void tryTest(Test suite, TestResult tr) {
        suite.run(tr);
        System.out.println();
        print(tr);
    }

    @Override
    public void addError(Test test, Throwable t) {
        System.out.print("E");
    }

    @Override
    public void addFailure(Test test, Throwable t) {
        System.out.print("F");
    }

    @Override
    public void startTest(Test test) {
        System.out.print(".");
    }

    @Override
    public void endTest(Test test) {

    }

    /**
     * Prints failures to the standard output
     */
    public void print(TestResult result) {
        printErrors(result);
        printFailures(result);
        printHeader(result);
    }

    /**
     * Prints the errors to the standard output
     */
    public void printErrors(TestResult result) {
        if (result.errorCount() != 0) {
            if (result.errorCount() == 1)
                writer().println("There was "+result.errorCount()+" error:");
            else
                writer().println("There were "+result.errorCount()+" errors:");

            int i= 1;
            for (Iterator<TestFailure> e = result.errors(); e.hasNext(); i++) {
                TestFailure failure= e.next();
                writer().println(i+") "+failure.failedTest());
                writer().print(getFilteredTrace(failure.thrownException()));
            }
        }
    }

    /**
     * Prints failures to the standard output
     */
    public void printFailures(TestResult result) {
        if (result.failureCount() != 0) {
            if (result.failureCount() == 1)
                writer().println("There was " + result.failureCount() + " failure:");
            else
                writer().println("There were " + result.failureCount() + " failures:");
            int i = 1;
            for (Iterator<TestFailure> e = result.failures(); e.hasNext(); i++) {
                TestFailure failure= (TestFailure) e.next();
                writer().print(i + ") " + failure.failedTest());
                Throwable t= failure.thrownException();
                writer().print(getFilteredTrace(failure.thrownException()));
            }
        }
    }

    protected PrintStream writer() {
        return writer;
    }

    /**
     * Prints the header of the report
     */
    public void printHeader(TestResult result) {
        if (result.wasSuccessful()) {
            writer().println();
            writer().print("OK");
            writer().println (" (" + result.runCount() + " tests)");

        } else {
            writer().println();
            writer().println("FAILURES!!!");
            writer().println("Tests run: "+result.runCount()+
                    ",  Failures: "+result.failureCount()+
                    ",  Errors: "+result.errorCount());
        }
    }

    /**
     * Returns a filtered stack trace
     */
    public static String getFilteredTrace(Throwable t) {
        StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer= stringWriter.getBuffer();
        String trace= buffer.toString();
        return trace;
        //return BaseTestRunner.filterStack(trace);
    }
}
