package com.coderising.junit;

public class TestSetUp extends TestDecorator {

	public TestSetUp(Test test) {
		super(test);
	}

	public void run(final TestResult testResult){
		Protectable p = new Protectable() {
			@Override
			public void protect() throws Throwable {
				setUp();
				basicRun(testResult);
				tearDown();
			}
		};
		
		testResult.runProtected(this, p);
	}

	protected void tearDown() {
		
	}

	protected void setUp() {
		
	}
	
}
