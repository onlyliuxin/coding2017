package com.coding.basic;

public class ArrayList implements List {

	private int size = 0;
	private static final int DEFAULT_SIZE = 100	;
	private Object[] elements = new Object[DEFAULT_SIZE];
	private int capacity = size;

	public void add(Object o) {
		addSize(size);
		this.elements[size] = o;
		this.size++;
	}

	public void add(int index, Object o) {
		checkIndex(index);
		for (int i = index; i < elements.length; i++) {
			if (i + 1 < elements.length) {
				elements[i] = elements[i + 1];
			}
		}
		size--;
	}

	public Object get(int index) {
		checkIndex(index);
		return this.elements[index];
	}

	public Object remove(int index) {
		checkIndex(index);
		Object o = elements[index];;
		for (int i = index; i < elements.length; i++) {
			
			if (i + 1 < elements.length) {
				elements[i] = elements[i + 1];
			}
		}
		size--;
		return o;
	}

	public int size() {
		return this.size;
	}

	public Iterator iterator() {
		return new IteratorImpl();
	}

	/**
	 * 判断是否越界
	 */
	private void checkIndex(int index) {
		if (index > size || index < 0) {
			throw new RuntimeException("越界");
		}
	}

	/**
	 * 确保当前数组容量，不足增加。
	 */
	private void addSize(int index) {
		if (index > size && size< elements.length-1) {
			this.capacity = this.size + this.DEFAULT_SIZE;
			Object[] newElemets = new Object[this.capacity];
			
			System.arraycopy(elements,0,newElemets,0,elements.length);

			this.elements = newElemets;
		}
	}
	
	class IteratorImpl implements Iterator {

		private int curi = 0;

		@Override
		public boolean hasNext() {
			boolean bn = false;
			if (curi < size) {
				bn = true;
			}
			return bn;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				return null;
			}
			curi++;
			return elements[curi];
		}

	}

}
