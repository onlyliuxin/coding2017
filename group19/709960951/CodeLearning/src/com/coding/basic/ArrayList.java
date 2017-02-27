package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	private static final int INI_SIZE = 100;
	private static final int EXTENDED_SIZE = 100; // 每次扩容的大小
	private int size = 0;
	private Object[] elements = new Object[INI_SIZE];

	@Override
	public void add(int index, Object o) {
		if (size < 1) {
			index = 0;
		} else {
			if (index < 0) {
				index = 0;
			}
			if (index > size - 1) {
				index = size - 1;
			}
		}

		ensureCapacity();
		for (int i = size - 1; i >= index; i--) {
			elements[i + 1] = elements[i];
		}
		elements[index] = o;
		size++;
	}

	@Override
	public void add(Object o) {
		ensureCapacity();
		elements[size] = o;
		size++;
	}

	@Override
	public Object get(int index) {
		if (size < 1 || index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		return elements[index];
	}

	@Override
	public Object remove(int index) {
		if (size < 1 || index < 0 || index > size - 1) {
			throw new IndexOutOfBoundsException();
		}
		Object object = elements[index];
		for (int i = index; i < size - 1; i++) {
			elements[i] = elements[i + 1];
		}
		elements[size - 1] = null;
		size--;
		adjustCapacity(); // 调整数组至合适大小
		return object;
	}

	@Override
	public int size() {
		return size;
	}

	// 底层数组最多有2*EXTENDED_SIZE个多余空间
	private void adjustCapacity() {
		if ((size + 2 * EXTENDED_SIZE) < elements.length) {
			elements = Arrays.copyOf(elements, size + 2 * EXTENDED_SIZE);
		}
	}

	// 每次添加元素时，检查底层数组的长度，保证存储空间
	private void ensureCapacity() {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, elements.length + EXTENDED_SIZE);
		}
	}

	public Iterator iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {

		private int curIndex = 0;

		@Override
		public boolean hasNext() {
			if (size > 0 && curIndex < size) {
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			Object object = elements[curIndex];
			curIndex++;
			return object;
		}

	}

}
