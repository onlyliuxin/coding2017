package com.coding.basic;

public class Queue {

	private Object[] elementData = new Object[100];

	int size = 0;

	public void enQueue(Object o) {
		if (size < 0 || size >= 100) {
			throw new IndexOutOfBoundsException();
		}
		elementData[size++] = o;
	}

	public Object deQueue() {
		if (size <= 0) {
			throw new IndexOutOfBoundsException();
		}
		Object o = elementData[0];
		int i = 0;
		for (; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[i] = null;
		size--;
		return o;
	}

	public boolean isEmpty() {
		return elementData.length != 0;
	}

	public int size() {
		return elementData.length;
	}
}