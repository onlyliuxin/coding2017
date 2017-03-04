package com.leaning.code;

import java.util.Arrays;

public class ArrayList implements List {

	private int size; // 记录集合中的元素个数

	private Object[] elementsData;

	private int totalCount = 1; // 记录集合的大小

	public ArrayList() {
		this.elementsData = new Object[totalCount];
	}

	private void grow() {
		if (size >= totalCount) {
			// 进行扩容
			int oldCapacity = size;
			int newCapacity = oldCapacity + oldCapacity << 1;
			totalCount = newCapacity;
			elementsData = Arrays.copyOf(elementsData, newCapacity);
		}
	}

	@Override
	public void add(Object o) {
		if (totalCount > size) {
			elementsData[size++] = o;
		} else {
			grow();
			elementsData[size++] = o;
		}
	}

	@Override
	public void add(int index, Object o) {
		if (index < size) {
			if (totalCount <= size + 1) {
				grow();
			}
			System.arraycopy(elementsData, index, elementsData, index + 1, size
					- index);
			elementsData[index] = 0;

		} else {
			throw new RuntimeException("数组下标越界");
		}
		size++;
	}

	@Override
	public Object get(int index) {
		if (index < size)
			return elementsData[index];
		else
			throw new RuntimeException("数组下标越界");
	}

	@Override
	public Object remove(int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException("数组下标越界");
		}
		Object o = elementsData[index];

		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementsData, index + 1, elementsData, index,
					numMoved);
		elementsData[--size] = null; 

		return o;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		return "ArrayList [elementsData=" + Arrays.toString(elementsData) + "]";
	}

}
