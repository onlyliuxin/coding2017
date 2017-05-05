/*
 * An automatic runner for Junit test for DataStructure assignment 
 * */
package com.github.congcongcong250.coding2017.basicTest;


import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {

	public static void main(String[] args){
		WArrayListTest ALT = new WArrayListTest();
		test(ALT);
		
		WLinkedListTest LLT = new WLinkedListTest();
		test(LLT);
		
		WStackTest STT = new WStackTest();
		test(STT);
		
		WQueueTest QT = new WQueueTest();
		test(QT);
		
		WBinaryTreeNodeTest BTNT = new WBinaryTreeNodeTest();
		test(BTNT);
	    
	}
	
	private static Result test(testCase tc){

		Result result = JUnitCore.runClasses(tc.getClass());
	    for (Failure failure : result.getFailures()) {
	    	System.out.println(failure.toString());
	    }
	    System.out.println(tc.getClass().toString()+ "\n>>> Test status: "+result.wasSuccessful());
	    
	    return result;
	}
}
