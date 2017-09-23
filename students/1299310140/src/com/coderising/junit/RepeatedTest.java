package com.coderising.junit;

public class RepeatedTest extends TestDecorator {

	private int fTimesRepeat;
	
	public RepeatedTest(Test test,int repeat) {
		super(test);
		if(repeat < 0)
			throw new IllegalArgumentException("repeation count must be > 0");
		this.fTimesRepeat = repeat;
	}
	
	public int countTestCases(){
		return super.countTestCases() * fTimesRepeat;
	}
	
	public void run(TestResult testResult){
		for(int i = 0;i < fTimesRepeat;i++){
			super.run(testResult);
		}
	}
}
