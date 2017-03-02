package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity > 100) {
			grow(elementData);
		}
	}

	private void grow(Object[] elementData) {
		int oldLength = elementData.length;
		int newLength = oldLength * 2;
		Arrays.copyOf(elementData, newLength);
	}

	public void add(int index, Object o) {
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
	}

	public Object get(int index) {
		if (index < 0 || index > elementData.length) {
			throw new IndexOutOfBoundsException();
		}
		return (Object) elementData[index];
	}

	public Object remove(int index) {
		int lowLength = size - index - 1;
		System.arraycopy(elementData, index + 1, elementData, index, lowLength);
		elementData[--size] = null;
		return (Object) elementData[index];
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return null;
	}

}
