/**
 * 
 */
package org.litejunit.v3.internal.runners;


import org.litejunit.v3.runner.Description;
import org.litejunit.v3.runner.Runner;
import org.litejunit.v3.runner.notification.Failure;
import org.litejunit.v3.runner.notification.RunNotifier;

public class ErrorReportingRunner extends Runner {
	private final Description fDescription;

	private final Throwable fCause;

	public ErrorReportingRunner(Description description, Throwable cause) {
		fDescription= description;
		fCause= cause;
	}

	@Override
	public Description getDescription() {
		return fDescription;
	}

	// TODO: this is duplicated in TestClassMethodsRunner
	@Override
	public void run(RunNotifier notifier) {
		notifier.fireTestStarted(fDescription);
		notifier.fireTestFailure(new Failure(fDescription, fCause));
		notifier.fireTestFinished(fDescription);
	}
}