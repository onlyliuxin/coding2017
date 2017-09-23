package org.litejunit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.litejunit.runner.TestListener;

public class TestResult {

	private List<TestFailure> fails;
	private List<TestFailure> errs;
	private int testCount;
	private List<TestListener> listeners;

	public TestResult() {
		fails = new ArrayList<>();
		errs = new ArrayList<>();
		listeners = new ArrayList<>();
		testCount = 0;
	}

	public int failsCount() {
		return fails.size();
	}

	public void run(final TestCase test) {
		startTest(test);
		try {
			test.doRun(this);
		} catch (AssertionFailedError e) {
			addFail(test, e);
		} catch (Throwable e) {
			addErr(test, e);
		} finally {
			endTest(test);
		}
	}

	private void endTest(Test test) {
		for (Iterator<TestListener> iter = listeners(); iter.hasNext();) {
			TestListener listener = iter.next();
			listener.endTest(test);
		}
	}

	private void startTest(Test test) {
		testCount += test.getCaseCount();

		for (Iterator<TestListener> iter = listeners(); iter.hasNext();) {
			TestListener listener = iter.next();
			listener.startTest(test);
		}
	}

	private void addErr(final Test test, Throwable e) {
		errs.add(new TestFailure(test, e));

		for (Iterator<TestListener> iter = listeners(); iter.hasNext();) {
			TestListener listener = iter.next();
			listener.addErr(test, e);
		}
	}

	private void addFail(final Test test, AssertionFailedError e) {
		fails.add(new TestFailure(test, e));
		for (Iterator<TestListener> iter = listeners(); iter.hasNext();) {
			TestListener listener = iter.next();
			listener.addFail(test, e);
		}
	}

	public Iterator<TestFailure> fails() {
		return fails.iterator();
	}

	public Iterator<TestFailure> errs() {
		return errs.iterator();
	}

	public int errsCount() {
		return errs.size();
	}

	public int runCount() {
		return testCount;
	}

	public boolean isSuccesful() {
		return failsCount() == 0 && errsCount() == 0;
	}

	public void addListener(TestListener listener) {
		listeners.add(listener);
	}

	public void removeListener(TestListener listener) {
		listeners.remove(listener);
	}

	private Iterator<TestListener> listeners() {
		return listeners.iterator();
	}
}
