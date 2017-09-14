package org.litejunit.v2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class TestResult extends Object {
	protected List<TestFailure> failures;
	protected List<TestFailure> errors;
	protected List<TestListener> listeners;
	protected int testCount;
	private boolean stop;
	
	public TestResult() {
		failures= new ArrayList<>();
		errors= new ArrayList<>();	
		listeners = new ArrayList<>();
		testCount= 0;
		stop= false;
	}
	
	public  void addError(Test test, Throwable t) {
		errors.add(new TestFailure(test, t));		
		for(TestListener listener: listeners){
			listener.addError(test, t);
		}
	}
	
	public  void addFailure(Test test, AssertionFailedError t) {
		failures.add(new TestFailure(test, t));		
		for(TestListener listener: listeners){
			listener.addFailure(test, t);
		}
	}
	
	public void startTest(Test test) {
		int count= test.countTestCases();
		testCount+= count;	
		for(TestListener listener: listeners){
			listener.startTest(test);
		}
	}
	public void endTest(Test test) {		
		for(TestListener listener: listeners){
			listener.endTest(test);
		}
	}
	
	/**
	 * Runs a TestCase.
	 */
	protected void run(final TestCase test) {
		startTest(test);		
		try {
			test.doRun();
		} 
		catch (AssertionFailedError e) {
			addFailure(test, e);
		}
		catch (Throwable e) {
			addError(test, e);
		}

		endTest(test);
	}
	/**
	 * Gets the number of run tests.
	 */
	public  int runCount() {
		return testCount;
	}
	public void runProtected(final Test test, Protectable p) {
		try {
			p.protect();
		} 
		catch (AssertionFailedError e) {
			addFailure(test, e);
		}
		catch (Throwable e) {
			addError(test, e);
		}
	}
	
	public  boolean shouldStop() {
		return stop;
	}
	
	public  void stop() {
		stop= true;
	}
	
	public  int errorCount() {
		return errors.size();
	}
	
	public  Iterator<TestFailure> errors() {
		return errors.iterator();
	}
	
	public  int failureCount() {
		return failures.size();
	}
	
	public  Iterator<TestFailure> failures() {
		return failures.iterator();
	}
	/**
	 * Returns whether the entire test was successful or not.
	 */
	public  boolean wasSuccessful() {
		return this.failureCount() == 0 && this.errorCount() == 0;
	}
	public  void addListener(TestListener listener) {
		listeners.add(listener);
	}
	
	public  void removeListener(TestListener listener) {
		listeners.remove(listener);
	}
}