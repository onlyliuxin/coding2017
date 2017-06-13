package com.coding.basic;

public class Stack {

	private ArrayList elementData = new ArrayList();

	public void push(Object o) {
		int size = elementData.size();
		elementData.add(size, o);
	}

	public Object pop() {
		int size = elementData.size();
		return elementData.remove(size - 1);
	}

	public Object peek() {
		int size = elementData.size();
		return elementData.get(size - 1);
	}

	public boolean isEmpty() {
		return elementData.size() != 0;
	}

	public int size() {
		return elementData.size();
	}	
}