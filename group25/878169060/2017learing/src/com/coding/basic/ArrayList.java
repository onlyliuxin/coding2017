package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	// 定义数组的长度为size是为了让外界无法得知咱们的数组是多长，而只是让外界知道放入数后的长度
	private int size = 0;

	private Object[] elementData = new Object[100];

	// 添加数据
	public void add(Object o) {
		ensureCapacity(size + 1);
		elementData[size] = o;
		size++;
	}

	public void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = (oldCapacity * 3) / 2 + 1; 
			if (newCapacity < minCapacity) {
				newCapacity = minCapacity;
			}
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	// 添加数据到指定的位置
	public void add(int index, Object o) {
		size = elementData.length;
		// 如果传入的值超过数组范围或者小于0
		checkIndex(index);
		ensureCapacity(size+1);
		// 将数组赋值
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		elementData = Arrays.copyOf(elementData,++size);
	}

	// 获取指定位置的数值
	public Object get(int index) {
		checkIndex(index);
		return elementData[index];
	}

	private void checkIndex(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("index" + index + "越界");
		}
	}

	// 移除指定位置的值
	public Object remove(int index) {
		checkIndex(index);
		Object oldValue = elementData[index];

		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);
		}
		elementData[--size] = null;// GC
		return oldValue;
	}

	// 获取数组的长度
	public int size() {
		return size;
	}
	//迭代器变量
	public Iterator iterator() {
		return new Itr();
	}
	//1.8默认实现hasNext next
	private class Itr implements Iterator {
		int cursor = 0;

		public boolean hasNext() {
			if (cursor < size) {
				return true;
			} //
			return false;
		}

		
		public Object next() {
			int nowCursor = cursor;
			cursor++;
			return elementData[nowCursor];
		}
	}

	
}
