package com.xusheng.stack;

public class TestMain {

	public static void main(String[] args) {
		MyLinkedStack<String> mls = new MyLinkedStack<String>();
		mls.add(0,"haha1");
		mls.add(1, "haha2");
		mls.push("haha3");
		System.out.println(mls.pop());
		System.out.println(mls.size());
		System.out.println(mls.pop());
		System.out.println(mls.size());
		System.out.println(mls.pop());
		System.out.println(mls.size());
	}
}
