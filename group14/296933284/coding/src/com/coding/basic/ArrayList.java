package com.coding.basic;

import java.util.Arrays;

/**
 * ArrayList 实现 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class ArrayList<T> implements List<T> {

	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] elementData;
	

	public ArrayList() {
		elementData = new Object[DEFAULT_CAPACITY];
	}

	@Override
	public boolean add(T element) {
		ensureCapacity(size + 1);
		elementData[size++] = element;
		return true;
	}

	@Override
	public void add(int index, T element) {
		rangCheckForAdd(index);

		ensureCapacity(size + 1);
		
		System.arraycopy(elementData, index, elementData, index + 1, size - index);

		elementData[index] = element;
		size++;
	}

	private void ensureCapacity(int minCapacity) {
		if (minCapacity - elementData.length > 0) {
			int newCapacity = elementData.length * 2;
			elementData = Arrays.copyOf(elementData, newCapacity);
		}
	}

	private void rangCheckForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public T get(int index) {
		rangCheck(index);

		return (T) elementData[index];
	}

	@Override
	public T remove(int index) {
		rangCheck(index);

		T element = (T) elementData[index];

		System.arraycopy(elementData, index + 1, elementData, index,size - index - 1);
		elementData[size - 1] = null;
		size--;

		return element;
	}

	private void rangCheck(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iter();
	}
	
	private class Iter implements Iterator<T> {
		int current;
		
		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public T next() {
			
			int i = current;
			rangCheck(i);
			current++;
			
			return (T) elementData[i];
		}
		
	}

}


