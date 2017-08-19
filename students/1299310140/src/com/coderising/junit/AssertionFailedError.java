package com.coderising.junit;

public class AssertionFailedError extends Error {
	
	public AssertionFailedError(){
		
	}
	
	public AssertionFailedError(String message){
		super(message);
	}
}
