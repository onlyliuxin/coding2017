package com.coderising.junit;

public class TestDecorator implements Test {

	protected Test test;
	
	public TestDecorator(Test test){
		this.test = test;
	}
	
	@Override
	public void run(TestResult tr) {
		basicRun(tr);
	}

	public void basicRun(TestResult tr) {
		this.test.run(tr);
	}

	@Override
	public int countTestCases() {
		
		return test.countTestCases();
	}

}
