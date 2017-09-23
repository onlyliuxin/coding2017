package com.coderising.litejunit.v;

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