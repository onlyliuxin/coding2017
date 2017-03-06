
package com.github.eloiseSJTU.coding2017.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		ensureCapacity(size + 1);
		
		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		checkBoundsForAdd(index);
		
		ensureCapacity(size + 1);
		
		if (index < size) {
			System.arraycopy(elementData, index, elementData, index + 1, size - index);
		}
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		checkBounds(index);
		
		return elementData[index];
	}

	public Object remove(int index) {
		checkBounds(index);
		
		Object o = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		elementData[--size] = null;
		return o;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Itr();
	}
	
	private class Itr implements Iterator {
		
		private int cur;

		@Override
		public boolean hasNext() {
			return cur != size;
		}

		@Override
		public Object next() {
			if (cur >= size) {
				throw new NoSuchElementException();
			}
			return elementData[cur++];
		}
		
	}

	private void ensureCapacity(int capacity) {
		if (capacity > elementData.length) {
			capacity = elementData.length << 1;
			elementData = Arrays.copyOf(elementData, capacity);
		}
	}

	private void checkBounds(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkBoundsForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException();
		}
	}
}
