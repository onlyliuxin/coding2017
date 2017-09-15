package org.v3.runner;

import org.v3.notification.RunNotifier;


public abstract class Runner {
	/**
	 * @return a <code>Description</code> showing the tests to be run by the receiver
	 */
	public abstract Description getDescription();

	/**
	 * Run the tests for this runner.
	 * @param notifier will be notified of events while tests are being run--tests being started, finishing, and failing
	 */
	public abstract void run(RunNotifier notifier);
	
	/**
	 * @return the number of tests to be run by the receiver
	 */
	public int testCount() {
		return getDescription().testCount();
	}
}