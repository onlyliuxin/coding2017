package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[10];

	/**
	 * 扩容
	 */
	private void expansion() {
		if (elementData.length <= size)
			elementData = Arrays.copyOf(elementData, elementData.length * 3 / 2 + 1);
	}

	/**
	 * 越界
	 */
	private void outOfBoundsForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("数组下标越界");
	}

	private void outOfBoundsForOther(int index) {
		if (index >= size || index < 0)
			throw new IndexOutOfBoundsException("数组下标越界");
	}

	public void add(Object o) {
		expansion();
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		outOfBoundsForAdd(index);
		expansion();
		for (int i = size - 1; i >= index; i--) {
			elementData[i + 1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		outOfBoundsForOther(index);
		return elementData[index];
	}

	public Object remove(int index) {
		outOfBoundsForOther(index);
		Object re = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size - 1] = null;
		size--;
		return re;
	}

	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return Arrays.toString(elementData);
	}

	public Iterator iterator() {
		return new ArrayIterator();
	}

	private class ArrayIterator implements Iterator {

		int pos = -1;

		@Override
		public boolean hasNext() {
			return size > ++pos ? true : false;
		}

		@Override
		public Object next() {
			return elementData[pos];
		}

	}

}
