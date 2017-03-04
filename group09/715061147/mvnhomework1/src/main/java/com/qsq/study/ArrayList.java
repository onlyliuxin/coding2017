package com.qsq.study;

import java.util.Arrays;

public class ArrayList<E> implements List<E> {

	private static final int DEFAULT_CAPACITY = 16;
	private static final Object[] EMPTY_ELEMENT_DATA = {};
	private Object[] elementData;
	private int size = 0;

	/*
	 * ����һ��Ĭ��������ArrayList
	 */
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/*
	 * ����һ��ָ����ʼ������ArrayList
	 * 
	 * @param initialCapacity ��ʼ����
	 * 
	 * @throws IllegalArgumentException ָ����ʼ����Ϊ����ʱ�׳��Ƿ������쳣
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
	 * ����elementData��������
	 * 
	 * @param capacity �µ���������
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
		
		// TODO: JDK��ʵ��: 1.Ч�� 2.GC����
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
		// TODO: JDKԴ����δ��index<0�����������Ϊ�Σ�
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
