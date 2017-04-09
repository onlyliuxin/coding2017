package com.pxshuo.test;

import com.pxshuo.se03.basic.LinkedList;

public class Test {
	public static void main(String[] args) {
		LinkedList obj = new LinkedList();
		obj.add("3");
		obj.add("7");
		obj.add("10");
		System.out.println(obj.getResult());
	}
}
