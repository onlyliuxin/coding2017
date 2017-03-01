package com.qsq.study;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

	private static final int DEFAULT_CAPACITY = 16;
	private static final Object[] EMPTY_ELEMENT_DATA = {};
	private Object[] elementData;
	private int size = 0;

	/*
	 * 构造一个默认容量的ArrayList
	 */
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/*
	 * 构造一个指定初始容量的ArrayList
	 * 
	 * @param initialCapacity 初始容量
	 * 
	 * @throws IllegalArgumentException 指定初始容量为负数时抛出非法参数异常
	 */
	public ArrayList(int initialCapacity) {
		if (initialCapacity > 0) {
			this.elementData = new Object[initialCapacity];
		} else if (initialCapacity == 0) {
			this.elementData = EMPTY_ELEMENT_DATA;
		} else {
			throw new IllegalArgumentException("Illegal Capacity " + initialCapacity);
		}
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/*
	 * 增加elementData数组容量
	 * 
	 * @param capacity 新的数组容量
	 */
	private void grow(int capacity) {
		if (capacity < elementData.length) {
			return;
		}
		elementData = Arrays.copyOf(elementData, capacity);
	}

	@Override
	public boolean add(E e) {
		if (size == elementData.length) {
			grow(size * 2);
		}
		elementData[size++] = e;
		return true;
	}

	@Override
	public E remove(int index) {
		rangeCheck(index);

		// move elements after index forward by 1
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		--size;
		
		// TODO: JDK中实现: 1.效率 2.GC回收
		// System.arraycopy(elementData, index + 1, elementData, index, size -
		// index - 1);
		// elementData[--size] = null;
		
		return null;
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);

		if (index < 0) {
			return false;
		}

		remove(index);
		return true;
	}

	@SuppressWarnings({ "unchecked" })
	private E elementData(int index) {
		return (E) elementData[index];
	}

	private void rangeCheck(int index) {
		// TODO: JDK源码中未对index<0的情况做处理，为何？
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index:" + index + ", Size:" + size);
		}
	}

	@Override
	public E get(int index) {
		rangeCheck(index);

		return elementData(index);
	}

	@Override
	public E set(int index, E element) {
		rangeCheck(index);

		E oldElement = elementData(index);
		elementData[index] = element;
		return oldElement;
	}

	@Override
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
		} else {
			for (int i = 0; i < size; i++) {
				if (o.equals(elementData[i])) {
					return i;
				}
			}
		}
		return -1;
	}

}
