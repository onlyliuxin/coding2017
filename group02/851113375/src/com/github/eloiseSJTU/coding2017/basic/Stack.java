package com.github.eloiseSJTU.coding2017.basic;

public class Stack {
	
	private int size = 0;
	
	private ArrayList elementData = new ArrayList();

	public void push(Object o) {
		elementData.add(o);
		size++;
	}

	public Object pop() {
		Object o = elementData.get(--size);
		elementData.remove(size);
		return o;
	}

	public Object peek() {
		return elementData.get(size - 1);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
