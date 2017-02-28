package com.vvv.base;

public class ArrayList implements IList {

	private static final int DEFAULT_SIZE = 100;

	private int size;
	private Object[] data = new Object[DEFAULT_SIZE];

	@Override
	public void add(Object o) {
		if (size > data.length) {
			increase();
		}
		data[size] = o;
		size++;
	}

	@Override
	public void add(int index, Object o) {
		check(index);

		if (index > data.length - 1) {
			increase();
		}
		System.arraycopy(data, index, data, index + 1, size - index);
		data[index] = o;
		size++;
	}

	private void check(int index) {
		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException("index: " + index + ", size: "
					+ size);
	}

	private void increase() {
		Object[] newData = new Object[DEFAULT_SIZE * 2];
		System.arraycopy(data, 0, newData, 0, size);
		data = newData;
	}

	@Override
	public Object get(int index) {
		check(index);
		return data[index];
	}

	@Override
	public Object remove(int index) {
		if (index > 0 && index < size) {
			Object d = data[index];
			int diff = size - index - 1;
			if (diff > 0)
				System.arraycopy(data, index + 1, data, index, size - index - 1);
			size--;
			data[size] = null;
			return d;
		}
		return null;
	}

	@Override
	public int size() {
		return this.size;
	}

}
