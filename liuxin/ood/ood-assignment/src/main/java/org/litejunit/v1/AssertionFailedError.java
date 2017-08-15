package org.litejunit.v1;

/**
 * Thrown when an assertion failed.
 */
public class AssertionFailedError extends Error {

	public AssertionFailedError () {
	}
	public AssertionFailedError (String message) {
		super (message);
	}
}