package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		if (size > 0)
			return elementData.remove(size);
		return null;
	}

	public Object peek() {
		return elementData.get(size);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
