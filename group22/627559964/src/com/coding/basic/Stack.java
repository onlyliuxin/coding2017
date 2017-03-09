package com.coding.basic;

public class Stack {
	
	private ArrayList elementData = new ArrayList();

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		Object obj = elementData.get(0);
		elementData.remove(0);
		return obj;
	}

	public Object peek() {
		return elementData.get(0);
	}

	public boolean isEmpty() {
		if (elementData.size() != 0) {
			return false;
		}
		return true;
	}

	public int size() {
		return elementData.size();
	}
}