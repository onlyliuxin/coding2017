package org.v0_my.runner;

import java.io.PrintStream;
import java.util.Iterator;

import org.v0_my.Test;
import org.v0_my.TestFailure;
import org.v0_my.TestResult;
import org.v0_my.sample.AllTest;

public class TestRunner extends TestBaseRunner {

	private PrintStream ps = System.out;

	public static void main(String[] args) {

		Test test = AllTest.tuite();
		TestResult tr = new TestResult();
		TestRunner runner = new TestRunner();
		tr.addListener(runner);
		long t1 = System.currentTimeMillis();
		test.run(tr);
		long t2 = System.currentTimeMillis();
		runner.ps.println();
		runner.ps.println("Time:" + runner.formatNum(t2 - t1) + "s");

		runner.printResult(tr);

	}

	private void printResult(TestResult tr) {
		printFails(tr);
		printErrs(tr);
		printSummary(tr);
	}

	private void printSummary(TestResult tr) {
		// TODO Auto-generated method stub
		String result = tr.isSuccesful() ? "Success" : "Fail";
		ps.println(result);
		ps.println("Test run:" + tr.runCount() + ",Failures:" + tr.failsCount() + ",Errs:" + tr.errsCount());
	}

	private void printErrs(TestResult tr) {
		// TODO Auto-generated method stub
		int count = tr.errsCount();
		String s = count > 1 ? "there were " + count + " errs" : "there was " + count + " err";
		ps.println(s);
		int i = 1;
		for (Iterator<TestFailure> iter = tr.errs(); iter.hasNext();) {
			TestFailure failure = iter.next();
			ps.print((i++) + ")");
			ps.println(failure.getTestCase());
			ps.println(failure.getThrowable());
		}
	}

	private void printFails(TestResult tr) {
		// TODO Auto-generated method stub
		int count = tr.failsCount();
		String s = count > 1 ? "there were " + count + " fails" : "there was " + count + " fail";
		ps.println(s);
		int i = 1;
		for (Iterator<TestFailure> iter = tr.fails(); iter.hasNext();) {
			TestFailure failure = iter.next();
			ps.print((i++) + ")");
			ps.println(failure.getTestCase());
			ps.println(failure.getThrowable());
		}
	}

}
