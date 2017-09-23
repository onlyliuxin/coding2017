package org.v0_my.runner;

import org.v0_my.AssertionFailedError;
import org.v0_my.Test;

public interface TestListener {
	public void endTest(Test test);

	public void startTest(Test test);
	
	public void addErr(final Test test, Throwable e) ;

	public void addFail(final Test test, AssertionFailedError e);
}
