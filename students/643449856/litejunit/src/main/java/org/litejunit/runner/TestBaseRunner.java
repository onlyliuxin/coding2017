package org.litejunit.runner;

import java.io.PrintStream;
import java.text.NumberFormat;

import org.litejunit.AssertionFailedError;
import org.litejunit.Test;

public class TestBaseRunner implements TestListener {
	private PrintStream pStream = System.out;
	int column = 0;

	@Override
	public synchronized void endTest(Test test) {
	}

	@Override
	public synchronized void startTest(Test test) {
		pStream.print(".");
		if (++column > 20) {
			pStream.println();
			column = 0;
		}
	}

	@Override
	public synchronized void addErr(Test test, Throwable e) {
	}

	@Override
	public synchronized void addFail(Test test, AssertionFailedError e) {
	}
public String formatNum(long num) {
	return NumberFormat.getInstance().format((double)(num/1000));
}
	
}
