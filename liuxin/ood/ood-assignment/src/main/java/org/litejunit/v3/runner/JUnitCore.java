package org.litejunit.v3.runner;

import java.util.ArrayList;
import java.util.List;

import org.litejunit.v3.notification.RunListener;
import org.litejunit.v3.notification.RunNotifier;
import org.litejunit.v3.runners.InitializationError;
import org.litejunit.v3.runners.TestClassRunner;
import org.litejunit.v3.runners.TextListener;



/**
 * <code>JUnitCore</code> is a facade for running tests. It supports running JUnit 4 tests, 
 * JUnit 3.8.2 tests, and mixtures. To run tests from the command line, run <code>java org.junit.runner.JUnitCore TestClass1 TestClass2 ...</code>.
 * For one-shot test runs, use the static method <code>runClasses(Class... classes)</code>
 * . If you want to add special listeners,
 * create an instance of <code>JUnitCore</code> first and use it to run the tests.
 * 
 * @see org.junit.runner.Result
 * @see org.junit.runner.notification.RunListener
 * @see org.junit.runner.Request
 */
public class JUnitCore {
	
	private RunNotifier notifier;

	/**
	 * Create a new <code>JUnitCore</code> to run tests.
	 */
	public JUnitCore() {
		notifier= new RunNotifier();
	}

	/**
	 * Run the tests contained in the classes named in the <code>args</code>.
	 * If all tests run successfully, exit with a status of 0. Otherwise exit with a status of 1.
	 * Write feedback while tests are running and write
	 * stack traces for all failed tests after the tests all complete.
	 * @param args names of classes in which to find tests to run
	 */
	/*public static void main(String... args) {
		Class<?> clz = null;
		try {
			clz = Class.forName(args[0]);			
		} catch (ClassNotFoundException e) {			
			e.printStackTrace();
			return;
		}		
		
		Request request = Request.aClass(clz);
		
		new JUnitCore().run(request);
		
		Result result= new JUnitCore().runMain(args);
		killAllThreads(result);
	}*/
    public static void  runClass(Class<?> clz){
    	try {
			TestClassRunner runner = new TestClassRunner(clz);
			JUnitCore core = new JUnitCore();
			core.addListener(new TextListener());			
			Result result = core.run(runner);
			
		} catch (InitializationError e) {
			
			e.printStackTrace();
		}
    	
    }
	/*private static void killAllThreads(Result result) {
		System.exit(result.wasSuccessful() ? 0 : 1);
	}*/
	
	/**
	 * Run the tests contained in <code>classes</code>. Write feedback while the tests
	 * are running and write stack traces for all failed tests after all tests complete. This is
	 * similar to <code>main()</code>, but intended to be used programmatically.
	 * @param classes Classes in which to find tests
	 * @return a <code>Result</code> describing the details of the test run and the failed tests.
	 */
	/*public static Result runClasses(Class... classes) {
		return new JUnitCore().run(classes);
	}*/
	
	/**
	 * Do not use. Testing purposes only.
	 */
	/*public Result runMain(String... args) {
		
		List<Class> classes= new ArrayList<Class>();
		for (String each : args)
			try {
				classes.add(Class.forName(each));
			} catch (ClassNotFoundException e) {
				System.out.println("Could not find class: " + each);
			}
		RunListener listener= new TextListener();
		addListener(listener);
		return run(classes.toArray(new Class[0]));
	}*/

	
	
	/**
	 * Run all the tests in <code>classes</code>.
	 * @param classes the classes containing tests
	 * @return a <code>Result</code> describing the details of the test run and the failed tests.
	 */
	/*public Result run(Class... classes) {
		return run(Request.classes("All", classes));
	}*/

	/**
	 * Run all the tests contained in <code>request</code>.
	 * @param request the request describing tests
	 * @return a <code>Result</code> describing the details of the test run and the failed tests.
	 */
	/*public Result run(Request request) {
		return run(request.getRunner());
	}*/

	/**
	 * Run all the tests contained in JUnit 3.8.x <code>test</code>. Here for backward compatibility.
	 * @param test the old-style test
	 * @return a <code>Result</code> describing the details of the test run and the failed tests.
	 */
	/*public Result run(junit.framework.Test test) { 
		return run(new OldTestClassRunner(test));
	}
	*/
	/**
	 * Do not use. Testing purposes only.
	 */
	public Result run(Runner runner) {
		Result result= new Result();
		RunListener listener= result.createListener();
		addListener(listener);
		
		try {
			notifier.fireTestRunStarted(runner.getDescription());
			runner.run(notifier);
			notifier.fireTestRunFinished(result);
		} finally {
			removeListener(listener);
		}
		return result;
	}
	
	/**
	 * Add a listener to be notified as the tests run.
	 * @param listener the listener
	 * @see org.junit.runner.notification.RunListener
	 */
	public void addListener(RunListener listener) {
		notifier.addListener(listener);
	}

	/**
	 * Remove a listener.
	 * @param listener the listener to remove
	 */
	public void removeListener(RunListener listener) {
		notifier.removeListener(listener);
	}
}
