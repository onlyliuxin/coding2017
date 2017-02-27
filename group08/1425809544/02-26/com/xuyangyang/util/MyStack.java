package com;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack {

	private ArrayList elementDataArrayList = new ArrayList<>();

	private Object[] elementData;
	private int elementCount;
	private int size;

	public MyStack() {
		this.elementData = new Object[10];

	}

	public Object push(Object o) {
		addElement(o);
		return o;

	}

	public void addElement(Object o) {
		ensureCapacity(elementCount + 1);
		elementData[elementCount++] = o;
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity - elementData.length > 0) {
			grow(minCapacity);
		}

	}

	private void grow(int minCapacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity + (oldCapacity >> 1);
		if (newCapacity - minCapacity < 0) {
			newCapacity = minCapacity;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);

	}

	public synchronized Object pop() {
		Object o;
		int len = size();
		o = peek();
		removeElement(len - 1);

		return o;

	}

	private void removeElement(int index) {

		if (index >= elementCount) {
			throw new ArrayIndexOutOfBoundsException();
		} else if (index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		int j = elementCount - index - 1;
		if (j > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, j);
		}
		elementCount--;
		elementData[elementCount] = null;
	}

	private Object peek() {

		int len = size();
		if (len == 0) {
			throw new EmptyStackException();
		}
		return elementAt(len - 1);

	}

	private Object elementAt(int index) {
		if (index > +elementCount) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elementData[index];
	}

	private int size() {
		// TODO Auto-generated method stub
		return elementCount;
	}

}
