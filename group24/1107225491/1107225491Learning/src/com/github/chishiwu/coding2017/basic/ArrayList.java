package com.github.chishiwu.coding2017.basic;

import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0;
	Object[] elementData = new Object[100];

	// 动态添加元素
	public void add(Object o) {
		ensureCapacity(size + 1);
		elementData[size] = o;
		size++;

	}

	public void add(int index, Object o) {
		Check(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size
				- index);
		elementData[index] = o;
		size++;
	}

	// 动态扩容
	private void ensureCapacity(int minCapacity) {
		// TODO Auto-generated method stub
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	public void Check(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("index" + index + "越界");
		}
	}

	public Object get(int index) {
		Check(index);
		return elementData[index];
	}

	public Object remove(int index) {
		Check(index);
		// 备份
		Object oldValue = elementData[index];
		int num = size - index - 1;
		if (num > 0)
			System.arraycopy(elementData, index + 1, elementData, index + 1,
					num);
		elementData[--size] = null;
		return oldValue;

	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {

		private int currentIndex = 0;

		public boolean hasNext() {
			if (currentIndex >= size)
				return false;
			else
				return true;
		}

		public Object next() {
			Object o = elementData[currentIndex];
			currentIndex++;
			return o;
		}
	}

}
