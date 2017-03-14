package com.coding.basic;

import java.util.Arrays;

public class MyArrayList implements MyList {
	private final int GROW = 4;
	private int size = 0;

	private Object[] elementData = new Object[4];

	public void add(Object o) {
		if (size > elementData.length - 1) {
			elementData = Arrays.copyOf(elementData, elementData.length + GROW);
			elementData[size] = o;
		} else {
			elementData[size] = o;
		}
		size++;
	}

	public void add(int index, Object o) {
		Object[] target = new Object[elementData.length - index];
		for (int x = index, y = 0; x < elementData.length; x++, y++) {
			target[y] = elementData[x];
		}
		elementData = Arrays.copyOf(elementData, elementData.length + 1);
		size = index;
		// elementData[index] = o;
		elementData[size] = o;
		size++;
		for (int y = 0; y < target.length; y++) {
			// add(target[y]);
			elementData[size] = target[y];
			size++;
		}
	}

	public Object get(int index) {
		return elementData[index];
	}

	public Object remove(int index) {
		Object removeData = elementData[index];
		elementData[index] = null;
		Object[] target = Arrays.copyOfRange(elementData, index + 1, elementData.length);
		for (int x = index, y = 0; y < target.length; y++, x++) {
			elementData[x] = target[y];
		}
		size--;
		return removeData;
	}

	public int size() {
		return size;
	}

	public MyIteratorImpl iterator() {
		return new MyIteratorImpl();
	}

	private class MyIteratorImpl implements MyIterator {
		int index;

		public boolean hasNext() {
			return index != size;
		}

		public Object next() {
			int i = index;
			index = i + 1;
			return elementData[i];
		}

	}
}
