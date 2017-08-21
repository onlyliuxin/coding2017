package org.litejunit.v2;

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