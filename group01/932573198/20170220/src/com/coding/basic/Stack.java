package com.coding.basic;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();

	
	@Override
	public String toString() {
		return elementData+"";
	}

	private void emptyStack() {
		if (elementData.size() == 0)
			throw new EmptyStackException();
	}

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		emptyStack();
		return elementData.remove(elementData.size() - 1);
	}

	public Object peek() {
		emptyStack();
		return elementData.get(elementData.size() - 1);
	}

	public boolean isEmpty() {
		return elementData.size() == 0 ? true : false;
	}

	public int size() {
		return elementData.size();
	}
}
