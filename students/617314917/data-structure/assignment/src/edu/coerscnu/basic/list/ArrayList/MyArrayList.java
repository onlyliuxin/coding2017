package edu.coerscnu.basic.list.ArrayList;

import edu.coerscnu.basic.Iterator;
import edu.coerscnu.basic.list.MyList;

public class MyArrayList<E> implements MyList<E> {

	private final static int DEFAULT_CAPACITY = 16;

	private int capacity;

	private int size;

	private Object[] ele;

	public MyArrayList(int capacity) {
		if (capacity < 0)
			throw new IllegalArgumentException("Illegal Capacity: " + capacity);
		this.capacity = capacity;
		ele = new Object[capacity];
	}

	public MyArrayList() {
		this(DEFAULT_CAPACITY);
	}

	@Override
	public boolean add(E e) {
		add(size, e);
		return true;
	}

	@Override
	public boolean add(int index, E e) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		ensureCapacity(size + 1);
		for (int i = size; i > index; i--) {
			ele[i] = ele[i - 1];
		}
		size++;
		ele[index] = e;
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E set(int index, E e) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		E old = (E) ele[index];
		ele[index] = e;
		return old;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E get(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		return (E) ele[index];
	}

	@SuppressWarnings("unchecked")
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		E old = (E) ele[index];
		for (int i = index; i < size - 1; i++) {
			ele[i] = ele[i + 1];
		}
		ele[--size] = null;
		return old;
	}

	@Override
	public boolean clear() {
		for (int i = 0; i < size; i++) {
			ele[i] = null;
		}
		size = 0;
		return true;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new MyArrayListIterator();
	}

	private void ensureCapacity(int newCapacity) {
		if (newCapacity < capacity)
			return;
		Object[] old = ele;
		ele = new Object[capacity <<= 1];
		for (int i = 0; i < size; i++) {
			ele[i] = old[i];
			old[i] = null;
		}
	}

	private class MyArrayListIterator implements Iterator<E> {

		private int current;

		@Override
		public boolean hasNext() {
			return current < size;
		}

		@Override
		public Object next() {
			return ele[current++];
		}

	}
}
