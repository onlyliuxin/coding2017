package org.litejunit.v3.notification;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.litejunit.v3.runner.Description;


/**
 * A <code>Failure</code> holds a description of the failed test and the 
 * exception that was thrown while running it. In most cases the <code>Description</code>
 * will be of a single test. However, if problems are encountered while constructing the
 * test (for example, if a <code>@BeforeClass</code> method is not static), it may describe
 * something other than a single test.
 */
public class Failure {
	private final Description fDescription;
	private Throwable fThrownException;

	/**
	 * Constructs a <code>Failure</code> with the given description and exception.
	 * @param description a <code>Description</code> of the test that failed
	 * @param thrownException the exception that was thrown while running the test
	 */
	public Failure(Description description, Throwable thrownException) {
		fThrownException = thrownException;
		fDescription= description;
	}

	/**
	 * @return a user-understandable label for the test
	 */
	public String getTestHeader() {
		return fDescription.getDisplayName();
	}

	/**
	 * @return the raw description of the context of the failure.
	 */
	public Description getDescription() {
		return fDescription;
	}

	/**
	 * @return the exception thrown
	 */

	public Throwable getException() {
	    return fThrownException;
	}

	@Override
	public String toString() {
	    StringBuffer buffer= new StringBuffer();
	    buffer.append(getTestHeader() + ": "+fThrownException.getMessage());
	    return buffer.toString();
	}

	/**
	 * Convenience method
	 * @return the printed form of the exception
	 */
	public String getTrace() {
		StringWriter stringWriter= new StringWriter();
		PrintWriter writer= new PrintWriter(stringWriter);
		getException().printStackTrace(writer);
		StringBuffer buffer= stringWriter.getBuffer();
		return buffer.toString();
	}

	/**
	 * Convenience method
	 * @return the message of the thrown exception
	 */
	public String getMessage() {
		return getException().getMessage();
	}
}
