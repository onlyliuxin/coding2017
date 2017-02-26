package com.github.congcongcong250.coding2017.basicTest;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args){
		ArrayListTest ALT = new ArrayListTest();
		test(ALT);
		
		LinkedListTest LLT = new LinkedListTest();
		test(LLT);
		
	    
	}
	
	private static Result test(testCase tc){

		Result result = JUnitCore.runClasses(tc.getClass());
	    for (Failure failure : result.getFailures()) {
	    	System.out.println(failure.toString());
	    }
	    System.out.println(result.wasSuccessful());
	    
	    return result;
	}
}
