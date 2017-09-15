package org.v3.notification;


import org.v3.runner.Description;
import org.v3.runner.Result;

public class RunListener {

	/**
	 * Called before any tests have been run.
	 * @param description describes the tests to be run
	 */
	public void testRunStarted(Description description) throws Exception {
	}
	
	/**
	 * Called when all tests have finished
	 * @param result the summary of the test run, including all the tests that failed
	 */
	public void testRunFinished(Result result) throws Exception {
	}
	
	/**
	 * Called when an atomic test is about to be started.
	 * @param description the description of the test that is about to be run (generally a class and method name)
	 */
	public void testStarted(Description description) throws Exception {
	}

	/**
	 * Called when an atomic test has finished, whether the test succeeds or fails.
	 * @param description the description of the test that just ran
	 */
	public void testFinished(Description description) throws Exception {
	}

	/** 
	 * Called when an atomic test fails.
	 * @param failure describes the test that failed and the exception that was thrown
	 */
	public void testFailure(Failure failure) throws Exception {
	}

	/**
	 * Called when a test will not be run, generally because a test method is annotated with <code>@Ignored</code>.
	 * @param description describes the test that will not be run
	 */
	public void testIgnored(Description description) throws Exception {
	}

}


