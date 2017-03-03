package com.coding.basic;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private Object[] elements;

	private int size;

	public ArrayList(int initialCapacity) {
		super();
		if (initialCapacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
		this.elements = new Object[initialCapacity];
	}

	public ArrayList() {
		this(10);
	}

	public void add(Object obj) {
		ensureCapacity(size + 1);
		elements[size++] = obj;

	}

	public void add(int index, Object obj) {
		rangeCheck(index);
		ensureCapacity(size + 1);
		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = obj;
		size++;
	}

	public Object get(int index) {
		rangeCheck(index);
		return elements[index];
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object toRemove = elements[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elements, index + 1, elements, index, numMoved);
		elements[--size] = null;
		return toRemove;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new ArrayListIterator();
	}

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elements.length;
		if (minCapacity > oldCapacity) {
			int newCapacity = oldCapacity * 2;
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			elements = Arrays.copyOf(elements, newCapacity);
		}
	}

	private void rangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
	}

	private String outOfBoundsMsg(int index) {
		return "Index: " + index + ", Size: " + this.size;
	}
	
	private class ArrayListIterator implements Iterator{

        private int pos = 0;

        public boolean hasNext() {
            return pos != size;
        }

        public Object next() {
        	int i = pos;
        	if (i >= size)
                throw new NoSuchElementException();
            Object[] elements = ArrayList.this.elements;
            if (i >= elements.length)
                throw new ConcurrentModificationException();
            pos = i + 1;
            return (Object) elements[i];
        }
    }

}