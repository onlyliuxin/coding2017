package com.coding.basic;

import com.coding.basic.ArrayList;

public class Stack {
	private ArrayList elementData;
	private int size;
	private int top = 0;

	public Stack() {
		this.size = 0;
		this.elementData = new ArrayList(20);
	}

	public void push(Object o) {
		elementData.add(top, o);
		size++;
	}

	public Object pop() {
		elementData.remove(top);
		size--;
		return elementData.get(top);
	}

	public Object peek() {
		int len = size();
		if (len >= 0) {
			return elementData.get(len - 1);
		}
		return null;
	}

	public boolean isEmpty() {
		return false;
	}

	public int size() {
		return size;
	}
}


