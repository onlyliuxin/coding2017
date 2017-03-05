package com.coding.basic;

public class MyStack {
	private MyArrayList elementData = new MyArrayList();

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		if (elementData.size() > 0) {
			Object data = elementData.get(elementData.size() - 1);
			elementData.remove(elementData.size() - 1);
			return data;
		}
		return null;
	}

	public Object peek() {
		if (elementData.size() > 0) {
			return elementData.get(elementData.size() - 1);
		}
		return null;
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}
}
