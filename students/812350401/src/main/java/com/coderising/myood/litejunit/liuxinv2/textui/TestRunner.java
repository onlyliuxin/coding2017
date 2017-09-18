package com.coderising.myood.litejunit.liuxinv2.textui;


import com.coderising.myood.litejunit.liuxinv2.*;
import com.coderising.myood.litejunit.liuxinv2.runner.BaseTestRunner;
import java.util.*;

import java.io.PrintStream;

/**
 * Runner是测试的驱动者，同时也是Listener
 */
public class TestRunner extends BaseTestRunner {
	PrintStream writer= System.out;
	int column= 0;

	/**
	 * Constructs a TestRunner.
	 */
	public TestRunner() {
	}
	
	
	/**
	 * Always use the StandardTestSuiteLoader. Overridden from
	 * BaseTestRunner.
	 */
	/*public TestSuiteLoader getLoader() {
		return new StandardTestSuiteLoader();
	}*/

	public synchronized void addError(Test test, Throwable t) {
		writer().print("E");
	}
	
	public synchronized void addFailure(Test test, AssertionFailedError t) {
		writer().print("F");
	}
			
	
	
	public TestResult doRun(Test suite) {
		TestResult result= new TestResult();
		result.addListener(this);
		long startTime= System.currentTimeMillis();
		suite.run(result);
		long endTime= System.currentTimeMillis();
		long runTime= endTime-startTime;
		writer().println();
		writer().println("Time: "+elapsedTimeAsString(runTime));
		print(result);

		writer().println();

		
		return result;
	}

	
	
	public synchronized void startTest(Test test) {
		writer().print(".");
		if (column++ >= 40) {
			writer().println();
			column= 0;
		}
	}

	public void endTest(Test test) {
	}
	
	public static void main(String args[]) {
	    // todo: 实际中，是通过命令行传入进来的
	    args = new String[] {"com.coderising.myood.litejunit.liuxinv2.sample.AllTest"};
		TestRunner testRunner= new TestRunner();
		try {
			TestResult r= testRunner.start(args);
			if (!r.wasSuccessful()) 
				System.exit(-1);
			System.exit(0);
		} catch(Exception e) {
			System.err.println(e.getMessage());
			System.exit(-2);
		}
	}
	/**
	 * Prints failures to the standard output
	 */
	public synchronized void print(TestResult result) {
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
			for (Iterator<TestFailure> e= result.failures(); e.hasNext(); i++) {
				TestFailure failure= (TestFailure) e.next();
				writer().print(i + ") " + failure.failedTest());
				Throwable t= failure.thrownException();
				writer().print(getFilteredTrace(failure.thrownException()));
			}
		}
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
	 * Starts a test run. Analyzes the command line arguments
	 * and runs the given test suite.
	 */
	protected TestResult start(String args[]) throws Exception {
		if(args.length == 0){
			throw new Exception("Usage: TestRunner  testCaseName");
		}
		String testCase= args[0];	

		try {
			Test suite= getTest(testCase);
			return doRun(suite);
		}
		catch(Exception e) {
			throw new Exception("Could not create and run test suite: "+e);
		}
	}
		
	protected void runFailed(String message) {
		System.err.println(message);
		System.exit(-1);
	}
	
	/**
	 * Runs a suite extracted from a TestCase subclass.
	 */
	static public void run(Class testClass) {
		run(new TestSuite(testClass));
	}
	/**
	 * Runs a single test and collects its results.
	 * This method can be used to start a test run
	 * from your program.
	 * <pre>
	 * public static void main (String[] args) {
	 *     test.textui.TestRunner.run(suite());
	 * }
	 * </pre>
	 */
	static public void run(Test suite) {
		TestRunner aTestRunner= new TestRunner();
		aTestRunner.doRun(suite);
	}
	
	protected PrintStream writer() {
		return writer;
	}
	
	
}