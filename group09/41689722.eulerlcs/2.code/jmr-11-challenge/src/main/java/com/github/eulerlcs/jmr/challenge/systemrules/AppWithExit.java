package com.github.eulerlcs.jmr.challenge.systemrules;

public class AppWithExit {
	public static String message;

	public static void doSomethingAndExit() {
		message = "exit ...";
		System.exit(1);
	}

	public static void doNothing() {
	}
}