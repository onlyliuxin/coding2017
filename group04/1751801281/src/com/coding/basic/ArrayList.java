package com.coding.basic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[10];

	public void add(Object o) {
		// 进行扩容检查
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("数组越界异常");
		ensureCapacity(size + 1);
		// 对数组进行复制处理，目的就是空出index的位置插入o，并将index后的元素位移一个位置
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		if (index < 0 || index > elementData.length) {
			return null;
		} else if (index > size && index < elementData.length) {
			return null;
		} else {
			return elementData[index];
		}
	}

	public Object remove(int index) {
		if (index < 0 || index > elementData.length) {
			return null;
		} else {
			int numMoved = size - index - 1;
			if (numMoved > 0)
				System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
			elementData[--size] = null;
			return elementData[index];
		}
	}

	public int size() {
		return size;
	}

	public void ensureCapacity(int minCapacity) {
		if (minCapacity > elementData.length) {
			grow(minCapacity);
		}
	}

	public void grow(int minCapacity) {
		// 当前数组的长度
		int oldCapacity = elementData.length;
		// 最小需要的容量大于当前数组的长度则进行扩容
		if (minCapacity > oldCapacity) {
			Object oldData[] = elementData;
			// 新扩容的数组长度为旧容量的1.5倍+1
			int newCapacity = (oldCapacity * 3) / 2 + 1;
			// 如果新扩容的数组长度还是比最小需要的容量小，则以最小需要的容量为长度进行扩容
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			// 进行数据拷贝，Arrays.copyOf底层实现是System.arrayCopy()
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	public Iterator iterator() {
		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {
		int cursor; // index of next element to return
		int lastRet = -1; // index of last element returned; -1 if no such

		public boolean hasNext() {
			return cursor != size;
		}

		public Object next() {
			int i = cursor;
			if (i >= size)
				throw new NoSuchElementException();
			Object[] elementData = ArrayList.this.elementData;
			if (i >= elementData.length)
				throw new ConcurrentModificationException();
			cursor = i + 1;
			return elementData[lastRet = i];
		}
	}
}
