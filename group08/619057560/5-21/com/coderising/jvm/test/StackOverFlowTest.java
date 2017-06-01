package com.coderising.jvm.test;

public class StackOverFlowTest {

	public void stackOverFlowMethod(){    
        stackOverFlowMethod();    
    } 
	
	public static void main(String[] args) {
		new StackOverFlowTest().stackOverFlowMethod();
	}

}
