package com.coding.basic;

public class Test {
	public static void main(String[] args) {
		System.out.println(new A().e == new A().e);
	}
}
class A{
	public A() {
		e = E;
	}
	Object[] e;
	static Object[] E = {};
}
