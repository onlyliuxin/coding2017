package collection.concrete;

import java.util.Arrays;
import java.util.NoSuchElementException;

import collection.AbstractList;
import collection.Iterator;

public class ArrayList<E> extends AbstractList<E> {

	private E[] elements;
	private int size;
	private static final int INITIAL_SIZE = 16;

	public ArrayList() {
		elements = (E[]) new Object[INITIAL_SIZE];
		size = 0;
	}

	@Override
	public void add(E e) {
		checkCapacity();
		elements[size++] = e;
	}

	private void checkCapacity() {
		if (size >= MAX_SIZE)
			throw new IndexOutOfBoundsException("Reached max size");

		if (elements.length - size < INITIAL_SIZE)
			grow();
	}

	synchronized private void grow() {

		int newCapacity = size * 2;
		newCapacity = (newCapacity < 0 || newCapacity - MAX_SIZE > 0) ? MAX_SIZE : newCapacity;
		E[] target = (E[]) new Object[newCapacity];
		System.arraycopy(elements, 0, target, 0, size);
		elements = target;

	}

	public void add(int index, E e) {
		checkCapacity();
		if (index == size) {
			add(e);
			return;
		}
		checkIndex(index);
		synchronized (this) {
			System.arraycopy(elements, index, elements, index + 1, size - index + 1);
			elements[index] = e;
			size++;
		}
	}

	@Override
	public E get(int index) {
		checkIndex(index);
		return elements[index];
	}

	public E getLast() {
		return get(size - 1);
	}

	public void addLast(E e) {
		add(e);
	}

	public E removeLast() {
		checkIndex(size);
		return elements[--size];
	}

	public E remove(int index) {
		checkIndex(index);
		E result = elements[index];
		synchronized (this) {
			System.arraycopy(elements, index + 1, elements, index, size - index - 1);
			elements[--size] = null;
		}
		return result;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(elements);
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArrayList other = (ArrayList) obj;
		if (!Arrays.equals(elements, other.elements))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayListIterator<E>(this);
	}

	private class ArrayListIterator<E> implements Iterator<E> {

		private ArrayList<E> myArrayList;
		private int pos;

		public ArrayListIterator(ArrayList<E> arrayList) {
			myArrayList = arrayList;
			pos = 0;
		}

		@Override
		public boolean hasNext() {
			return pos < size;
		}

		@Override
		public E next() {
			if (hasNext())
				return (E) elements[pos++];
			throw new NoSuchElementException();
		}
	}

}
