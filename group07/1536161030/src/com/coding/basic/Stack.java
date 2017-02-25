package com.coding.basic;

import java.util.EmptyStackException;


public class Stack {
	private Object[] elementData;
	private int size;

	public Stack() {
		this.size = 0;
		this.elementData = new Object[10];
	}

	public void push(Object o) {
		if (o == null) 
			throw new RuntimeException("元素不可为NULL");
		size++;
		elementData[size -1]= o ;
	}

	public Object pop() {
		if (isEmpty()) 
			throw new EmptyStackException();
		Object old = elementData[size - 1];
		elementData[size] = null;
		size--;
		return old;
	}

	public Object peek() {
		if (isEmpty()) 
			throw new EmptyStackException();
		return elementData[size -1];
	}

	public boolean isEmpty() {
		return size < 1;
	}
	
	//
	public int size() {
		return size;
	}
}


