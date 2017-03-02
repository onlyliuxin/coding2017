package com.coding.basic;

public class Test {
	public static void main(String[] args) {
		LinkedList linkedList = new LinkedList();
		for (int i = 0; i < 10; i++) {
			linkedList.add(i);
		}
		for (int i = 0; i < 10; i++) {
			linkedList.removeLast();
		}
		linkedList.removeLast();
	}

}
