package week01.basic;

import java.util.Arrays;

public class MyArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		ensureCapacity(size + 1);

		elementData[size++] = o;
	}

	public void add(int index, Object o) {
		checkPositionIndex(index);
		ensureCapacity(size + 1);

		if (index >= size) {
			elementData[size++] = o;
		} else {
			System.arraycopy(elementData, index, elementData, index + 1, size
					- index);

			elementData[index] = o;

			size++;
		}
	}

	public Object get(int index) {
		checkElementIndex(index);
		return elementData[index];
	}

	public Object remove(int index) {
		checkElementIndex(index);
		Object removeElement = elementData[index];
		if (index == (size - 1)) {
			elementData[index] = null;
			size--;
		} else {
			System.arraycopy(elementData, index + 1, elementData, index, size
					- index - 1);
			elementData[size - 1] = null;
			size--;
		}
		return removeElement;
	}

	public int size() {
		return size;
	}

	/**
	 * 保证数组空间充足
	 * 
	 * @param minCapacity
	 */
	private void ensureCapacity(int minCapacity) {
		int capacity = elementData.length;
		if (minCapacity > capacity) {
			capacity += capacity / 2;
			grow(capacity);
		}
	}

	private void checkElementIndex(int index) {
		if (!isElementIndex(index)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
	}

	private boolean isElementIndex(int index) {
		return index >= 0 && index < size;
	}

	private void checkPositionIndex(int index) {
		if (!isPositionIndex(index)) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: "
					+ size);
		}
	}

	private boolean isPositionIndex(int index) {
		return index >= 0 && index <= size;
	}

	private void grow(int newCapacity) {
		elementData = Arrays.copyOf(elementData, newCapacity);
	}

	public Iterator iterator() {
		return new ArrayListIterator(this);
	}

	private class ArrayListIterator implements Iterator {
		private MyArrayList list;
		private int position = 0;

		private ArrayListIterator(MyArrayList list) {
			this.list = list;
		}

		@Override
		public boolean hasNext() {
			if ((position + 1) > size) {
				return false;
			}
			return true;
		}

		@Override
		public Object next() {
			return list.get(position++);
		}
	}

	@Override
	public String toString() {
		String elementStr = "";
		for (int i = 0; i < size; i++) {
			elementStr += elementData[i] + ",";
		}
		return "MyArrayList: { size=" + size + ", elementData=" + "["
				+ elementStr.substring(0, elementStr.length() - 1) + "]" + " }";
	}
}
