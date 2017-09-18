package com.coderising.junit;

public class Calculator {
	
	private int result = 0;
	
	public void add(int x){
		result += x;
	}
	
	public void subtract(int x){
		result -= x;
	}
	
	public int getResult(){
		return result;
	}
	
	public static void main(String[] args){
		TestSuite ts = new TestSuite(CalculatorTest.class);
		TestResult tr = new TestResult();
		ts.run(tr);
		for(TestFailure testFailure:tr.getFailures()){
			System.out.println(testFailure.thrownException());
		}
		for(TestFailure testFailure:tr.getErrors()){
			System.out.println(testFailure.thrownException());
		}
	}
}
