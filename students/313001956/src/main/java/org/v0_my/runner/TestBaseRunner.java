package org.v0_my.runner;

import java.io.PrintStream;
import java.text.NumberFormat;

import org.v0_my.AssertionFailedError;
import org.v0_my.Test;

public class TestBaseRunner implements TestListener {
	private PrintStream pStream = System.out;
	int column = 0;

	@Override
	public synchronized void endTest(Test test) {
		// TODO Auto-generated method stub
		// pStream.println();
	}

	@Override
	public synchronized void startTest(Test test) {
		// TODO Auto-generated method stub
		pStream.print(".");
		if (++column > 20) {
			pStream.println();
			column = 0;
		}
	}

	@Override
	public synchronized void addErr(Test test, Throwable e) {
		// TODO Auto-generated method stub
		pStream.print("E");
	}

	@Override
	public synchronized void addFail(Test test, AssertionFailedError e) {
		// TODO Auto-generated method stub
		pStream.print("F");
	}
public String formatNum(long num) {
	return NumberFormat.getInstance().format((double)(num/1000));
}
	
}
