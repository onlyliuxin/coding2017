package com.basic;

public class ArrayList implements List
{

	private int size = 0;

	private final int MAXNSIZE = 100;

	private Object[] elementData = new Object[MAXNSIZE];

	public void add(Object o) {
		if (size > MAXNSIZE) {
			String errorInfo = "Out of max size" + MAXNSIZE;
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		if (index >= size && size > 0) {
			String errorInfo = "Index to add: " + index
					+ " is out of current size: " + size;
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
		++size;
	}

	public Object get(int index) {
		if (index < 0 || index >= size) {
			String errorInfo = "Index to get: " + index
					+ " is invalid, current range: 0 - " + (size - 1);
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}
		return elementData[index];
	}

	public Object remove(int index) {
		if (index < 0 || index >= size) {
			String errorInfo = "Index to remove: " + index
					+ " is invalid, current range: 0 - " + (size - 1);
			throw new ArrayIndexOutOfBoundsException(errorInfo);
		}

		Object o = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size--] = null;
		return o;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Iterator() {

			private int index = 0;

			public boolean hasNext() {
				return (index < size);
			}

			public Object next() {
				if (hasNext()) {
					return elementData[index++];
				}
				return null;
			}
		};
	}

}
