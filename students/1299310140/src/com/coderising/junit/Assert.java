package com.coderising.junit;

public class Assert {
	
	public static void assertEquals(int expected,int actual){
		if(expected == actual)
			return;
		failNotEquals(expected,actual);
	}

	private static void failNotEquals(int expected, int actual) {
		String message = "expected:<" + expected + "> but was:<" + actual + ">";
		fail(message);
	}

	private static void fail(String message) {
		throw new AssertionFailedError(message);
	}
}
