package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();

	public void push(Object o) {
		if (isEmpty()) {
			elementData.add(elementData.length() - 1, o);
		}
		elementData.add(elementData.length() - elementData.size() - 1, o);
	}

	public Object pop() {
		if (isEmpty()) {
			return null;
		}
		return elementData.remove(elementData.length() - elementData.size() - 1);
	}

	public Object peek() {
		if (isEmpty()) {
			return null;
		}
		return elementData.get(elementData.length() - elementData.size() - 1);
	}

	public boolean isEmpty() {
		if (elementData.size() == 0) {
			return true;
		}
		return false;
	}

	public int size() {
		return elementData.size();
	}

}
