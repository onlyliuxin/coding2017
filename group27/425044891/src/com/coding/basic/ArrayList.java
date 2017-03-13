package com.coding.basic;

/**
 * ArrayList
 * 
 * @author Guang
 * @date 2017年3月12日 下午1:55:47
 */
public class ArrayList implements List {

	/**
	 * 数组中元素个数
	 */
	private int size = 0;

	/**
	 * 数组
	 */
	private Object[] elementData = new Object[100];

	public void add(Object o) {
		if (size >= 100 || size < 0) {
			throw new IndexOutOfBoundsException("越界.");
		}
		if (null == o) {
			return;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		checkRangeForAdd(index);
		if (null == o) {
			return;
		}
		for (int i = size; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
		size++;
	}

	private void checkRangeForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException("越界.");
		}
	}

	private void checkRangeForGet(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("越界.");
		}
	}

	public Object get(int index) {
		checkRangeForGet(index);
		return elementData[index];
	}

	/**
	 * 移除第index位置的元素，后续元素前移
	 */
	public Object remove(int index) {
		checkRangeForGet(index);
		int i = index;
		Object o = elementData[index];
		for (; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[i] = null;
		size--;
		return o;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Iterator() {
			int index = 0;
			@Override
			public Object next() {
				checkRangeForGet(index);
				Object o = elementData[index];
				index++;
				return o;
			}

			@Override
			public boolean hasNext() {
				return index < size && index >= 0;
			}
		};
	}
}