package com.coderising.litejunit;

public class Calculator {
	private int result = 0;
	
	public void add(int num) {
		result += num;
	}
	
	public void substract(int num) {
		result -= num;
	}
	
	public int getResult() {
		return result;
	}
}
