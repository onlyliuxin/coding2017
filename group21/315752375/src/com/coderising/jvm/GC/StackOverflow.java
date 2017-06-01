package com.coderising.jvm.GC;

public class StackOverflow {

	private int count=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StackOverflow stackOverflow=new StackOverflow();
		stackOverflow.test();
	}
	public void test(){
		count++;
		System.out.println("count:"+count);
		test();
	}
}
