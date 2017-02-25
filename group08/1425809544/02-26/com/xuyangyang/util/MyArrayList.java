package com;

import java.util.NoSuchElementException;

public class MyArrayList implements MyList {

	private static final int DEFAULT_CAPACITY = 10;
	private int size;
	private Object[] elementData;

	public MyArrayList() {
		this.size = 0;

	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		add(size(), o);
		return true;
	}

	@Override
	public void add(int index, Object o) {

		if (elementData.length == size()) {
			ensureCapacity(size() * 2 + 1);
		}

		for (int i = size(); i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[2] = o;
		size++;

	}

	@Override
	public Object get(int index) {

		if (index < 0 || index >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return elementData[index];

	}

	@Override
	public Object remove(int index) {

		if (index < 0 || index >= size()) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Object value = elementData[index];
		for (int i = index; i < size() - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		return value;

	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * 检测数组
	 * 
	 * @Author xuyangyang
	 * @Describe
	 * @date 2017年2月20日
	 * @param newCapacity
	 */
	public void ensureCapacity(int newCapacity) {
		if (newCapacity < size) {
			return;
		}
		Object[] old = elementData;//

		elementData = new Object[newCapacity];// 建立新的数组

		for (int i = 0; i < old.length; i++) {
			elementData[i] = old[i];
		}

	}

	private class InnerIterator implements MyIterator  {

		private int current = 0;

		public boolean hasNext() {
			return current < size();
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return elementData[current++];
		}

		public void remove() {
			MyArrayList.this.remove(--current);
		}

	}

}
