package com.coding.basic;

//import java.util.Arrays;

public class ArrayList implements List {

	private int size = 0; // 记录数组当前长度

	private Object[] elementData = new Object[10]; // 初始长度

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.coding.basic.List#add(java.lang.Object)
	 */
	public void add(Object o) {
		if (size > elementData.length) { // size大于数组初始长度，需要对原数组进行扩容
			grow(elementData, 10);
		}
		this.elementData[size++] = o;
	}

	/*
	 * 在指定下标位置插入元素 (non-Javadoc)
	 * 
	 * @see com.coding.basic.List#add(int, java.lang.Object)
	 */
	public void add(int index, Object o) {
		if (size > elementData.length) { // size大于数组初始长度，需要对原数组进行扩容
			grow(elementData, 10);
		}
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		// 1、先要判断index所在处有无值，没有则返回null
		Object o = elementData[index];
		if (null == o)
			throw new IndexOutOfBoundsException();
		return o;
	}

	public Object remove(int index) {
		Object oldVal = elementData[index]; // 保留要删除的元素
		int numMoved = size - index - 1;
		if (numMoved > 0) {
			System.arraycopy(elementData, index + 1, elementData, index, numMoved);// 讲移除位置之后的元素向前 挪动
		}
		elementData[--size] = null; // 将数组末尾元素置为空
		return oldVal;
	}

	/**
	 * 获取数组元素个数
	 */
	public int size() {
		return this.size;
	}

	public Object[] grow(Object[] src, int size) {
		// Arrays.copyOf(src, src.length+size);
		Object[] target = new Object[src.length + size];
		System.arraycopy(src, 0, target, 0, src.length);
		return target;
	}

	public Iterator iterator() {

		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {

		private int cursor=0;
		@Override
		public boolean hasNext() {
			return size != cursor;
		}

		@Override
		public Object next() {
			return elementData[cursor++];
		}

	}

}
