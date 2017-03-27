package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();

	public Stack() {
		// TODO Auto-generated constructor stub
	}

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		int size = elementData.size();
		Object o = elementData.get(size - 1);
		elementData.remove(size - 1);
		return o;
	}

	public Object peek() {
		int size = elementData.size();
		Object o = elementData.get(size - 1);
		
		return o;
	}

	public boolean isEmpty() {
		if (elementData.size() == 0)
			return true;
		return false;
	}

	public int size() {
		return elementData.size();
	}
}
