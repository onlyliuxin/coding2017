package com.guodong.datastructure;

public class Stack {
	private LinkedList elementData = new LinkedList();

	public void push(Object o) {
		elementData.addLast(o);
	}

	public Object pop() {
		return elementData.removeLast();
	}

	public Object peek() {
		return elementData.getLast();
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}
