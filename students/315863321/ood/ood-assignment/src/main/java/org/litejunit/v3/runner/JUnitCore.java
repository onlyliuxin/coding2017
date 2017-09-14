package org.litejunit.v3.runner;

import org.litejunit.v2.Test;
import org.litejunit.v3.internal.runners.TextListener;
import org.litejunit.v3.runner.notification.RunListener;
import org.litejunit.v3.runner.notification.RunNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>JUnitCore</code> is a facade for running tests. It supports running JUnit 4 tests,
 * JUnit 3.8.2 tests, and mixtures. To run tests from the command line, run <code>java org.junit.runner.JUnitCore TestClass1 TestClass2 ...</code>.
 * For one-shot test runs, use the static method <code>runClasses(Class... classes)</code>
 * . If you want to add special listeners,
 * create an instance of <code>JUnitCore</code> first and use it to run the tests.
 *
 * @see Result
 * @see RunListener
 * @see Request
 */
public class JUnitCore {

    private RunNotifier fNotifier;

    /**
     * Create a new <code>JUnitCore</code> to run tests.
     */
    public JUnitCore() {
        fNotifier = new RunNotifier();
    }

    /**
     * Run the tests contained in the classes named in the <code>args</code>.
     * If all tests run successfully, exit with a status of 0. Otherwise exit with a status of 1.
     * Write feedback while tests are running and write
     * stack traces for all failed tests after the tests all complete.
     *
     * @param args names of classes in which to find tests to run
     */
    public static void main(String... args) {
        Result result = new JUnitCore().runMain(args);
        killAllThreads(result);
    }

    private static void killAllThreads(Result result) {
        System.exit(result.wasSuccessful() ? 0 : 1);
    }

    /**
     * Run the tests contained in <code>classes</code>. Write feedback while the tests
     * are running and write stack traces for all failed tests after all tests complete. This is
     * similar to <code>main()</code>, but intended to be used programmatically.
     *
     * @param classes Classes in which to find tests
     * @return a <code>Result</code> describing the details of the test run and the failed tests.
     */
    public static Result runClasses(Class... classes) {
        return new JUnitCore().run(classes);
    }

    /**
     * Do not use. Testing purposes only.
     */
    public Result runMain(String... args) {
        System.out.println("JUnit version " + "4.0");
        List<Class> classes = new ArrayList<Class>();
        for (String each : args)
            try {
                classes.add(Class.forName(each));
            } catch (ClassNotFoundException e) {
                System.out.println("Could not find class: " + each);
            }
        RunListener listener = new TextListener();
        addListener(listener);
        return run(classes.toArray(new Class[0]));
    }

    /**
     * @return the version number of this release
     */
    public String getVersion() {
        return "4.0";
    }

    /**
     * Run all the tests in <code>classes</code>.
     *
     * @param classes the classes containing tests
     * @return a <code>Result</code> describing the details of the test run and the failed tests.
     */
    public Result run(Class... classes) {
        return run(Request.classes("All", classes));
    }

    /**
     * Run all the tests contained in <code>request</code>.
     *
     * @param request the request describing tests
     * @return a <code>Result</code> describing the details of the test run and the failed tests.
     */
    public Result run(Request request) {
        return run(request.getRunner());
    }

    /**
     * Run all the tests contained in JUnit 3.8.x <code>test</code>. Here for backward compatibility.
     *
     * @param test the old-style test
     * @return a <code>Result</code> describing the details of the test run and the failed tests.
     */
    public Result run(Test test) {
//		return run(new OldTestClassRunner(test));
        return null;
    }

    /**
     * Do not use. Testing purposes only.
     */
    public Result run(Runner runner) {
        Result result = new Result();
        RunListener listener = result.createListener();
        addListener(listener);
        try {
            fNotifier.fireTestRunStarted(runner.getDescription());
            runner.run(fNotifier);
            fNotifier.fireTestRunFinished(result);
        } finally {
            removeListener(listener);
        }
        return result;
    }

    /**
     * Add a listener to be notified as the tests run.
     *
     * @param listener the listener
     * @see RunListener
     */
    public void addListener(RunListener listener) {
        fNotifier.addListener(listener);
    }

    /**
     * Remove a listener.
     *
     * @param listener the listener to remove
     */
    public void removeListener(RunListener listener) {
        fNotifier.removeListener(listener);
    }
}
