package GithubWork;

import java.util.Arrays;

public class ArrayList implements List {
	private int size = 0;
	private Object[] elementdata = new Object[100];

	public void add(Object o) {
		if (elementdata.length <= size) {
			ensureCapacity(size + 1);
		}
		elementdata[size++] = o;
	}

	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elementdata.length;
		if (oldCapacity < minCapacity) {
			
			int newCapacity = (int) (oldCapacity * 1.5);
			if (newCapacity < minCapacity)
				newCapacity = minCapacity;
			elementdata = Arrays.copyOf(elementdata, newCapacity);
		}
	}

	public void add(int index, Object o) {
		if (index > size || index < 0) {
			throw new IndexOutOfBoundsException();

		}

		ensureCapacity(size + 1);
		System.arraycopy(elementdata, index, elementdata, index, size - index);
		elementdata[index] = o;
		size++;
	}

	public Object get(int index) {
		RangeCheck(index);

		return elementdata[index];
	}

	public Object remove(int index) {
		RangeCheck(index);
		Object oldvalue = elementdata[index];
		int numMoved = size - index - 1;
		if (numMoved > 0)
			System.arraycopy(elementdata, index + 1, elementdata, index, numMoved);
		elementdata[--size] = null;
		return oldvalue;
	}

	private void RangeCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException();
	}

	public int size() {
		int i;
		for (i = 0; i < elementdata.length; i++) {
			size++;
			if (null == elementdata[i]) {
				break;
			}

		}
		return size;

	}

}
