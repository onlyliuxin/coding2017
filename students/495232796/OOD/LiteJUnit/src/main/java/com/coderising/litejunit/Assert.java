package com.coderising.litejunit;

public class Assert {
	static public void assertEquals(int expected, int actual) {
		assertEquals(null, new Integer(expected), new Integer(actual));
	}
	
	static public void assertEquals(String message, Object expected, Object actual) {
		if (expected == null && actual == null) {
			return ;
		}
		if (expected != null && expected.equals(actual)) {
			return ;
		}
		failNotEquals(message, expected, actual);
	}
	
	static public void fail(String message) {
		throw new AssertionFailedError(message);
	}
	
	static private void failNotEquals(String message, Object expected, Object actual) {
		String formatted= "";
		if (message != null)
			formatted= message+" ";
		fail(formatted+"expected:<"+expected+"> but was:<"+actual+">");
	}
}
