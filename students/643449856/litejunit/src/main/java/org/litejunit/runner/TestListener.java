package org.litejunit.runner;

import org.litejunit.AssertionFailedError;
import org.litejunit.Test;

public interface TestListener {
	public void endTest(Test test);

	public void startTest(Test test);
	
	public void addErr(final Test test, Throwable e) ;

	public void addFail(final Test test, AssertionFailedError e);
}
