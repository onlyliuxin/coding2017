package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[4];

	public void add(Object o) {
		ensureCapacity(size + 1);
		elementData[size] = o;
		size++;
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity > elementData.length) {
			int newCapacity = Math.max(minCapacity, elementData.length * 2);
			Object[] newArray = new Object[newCapacity];
			System.arraycopy(elementData, 0, newArray, 0, elementData.length);
			elementData = newArray;
		}
	}

	public void add(int index, Object o) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		ensureCapacity(size + 1);
		// shift and add element
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		// check input
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		return elementData[index];
	}

	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}

		if (size == 0) {
			return null;
		}

		// remove element and shift
		Object target = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);

		// reset last element
		elementData[size - 1] = null;
		size--;
		return target;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new SeqIterator();
	}

	private class SeqIterator implements Iterator {
		int i = 0;

		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				return null;
			}
			return elementData[i++];
		}

	}

}
