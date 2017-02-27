package com.coding.basic;

public class Stack {

	private LinkedList linkedList;

	public Stack() {
		linkedList = new LinkedList();
	}

	public void push(Object o) {
		linkedList.add(o);
	}

	public Object pop() {
		return linkedList.removeLast();
	}

	public Object peek() {
		return linkedList.get(linkedList.size()-1);
	}

	public boolean isEmpty() {
		return linkedList.size() == 0;
	}

	public int size() {
		return linkedList.size();
	}
}
