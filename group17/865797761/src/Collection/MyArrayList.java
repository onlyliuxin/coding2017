package Collection;

import java.util.Arrays;

public class MyArrayList<E> implements List<E>, Iterable<E> {
	private Object[] elementData;
	private static final int DEFAULT_SIZE = 10;
	private int size;

	public MyArrayList() {
		this(DEFAULT_SIZE);
	}

	public MyArrayList(int initSize) {
		if (initSize < 0) {
			throw new IllegalArgumentException(initSize + " < 0");
		}
		if (initSize == 0) {
			elementData = new Object[DEFAULT_SIZE];
		}
		else {
			elementData = new Object[initSize];
		}
		size = 0;
	}

	public void add(E o) {
		growIfNeed();
		elementData[size++] = o;
	}

	public void add(int index, E o) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException("index:" + index);
		}
		growIfNeed();
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	@SuppressWarnings("unchecked")
	public E get(int index) {
		rangeCheck(index);
		return (E) elementData[index];
	}

	public E remove(int index) {
		rangeCheck(index);
		E target = get(index);
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return target;
	}

	public int size() {
		return size;
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new NoSuchElementException("index:" + index);
		}
	}

	private void growIfNeed() {
		if (size == elementData.length)
			grow();
	}

	private void grow() {
		elementData = Arrays.copyOf(elementData, elementData.length * 2);
	}

	@Override
	public Iterator<E> iterator() {
		return new ArrayIterator<>();
	}

	private class ArrayIterator<E> implements Iterator<E> {
		private int currentPos = 0;

		@Override
		public boolean hasNext() {
			return currentPos < size;
		}

		@SuppressWarnings("unchecked")
		@Override
		public E next() {
			rangeCheck(currentPos);
			return (E) elementData[currentPos++];
		}

	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(elementData, size));
	}

}

class NoSuchElementException extends RuntimeException {

	public NoSuchElementException(String string) {
		super(string);
	}

}