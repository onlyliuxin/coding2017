package main;

import java.util.Arrays;

import utils.ListUtils;

public class ArrayList implements List {
	private Object[] elementData = new Object[10];
	private int size;

	@Override
	public void add(Object o) {
		add(size, o);

	}

	@Override
	public void add(int index, Object o) {
		// 检查是否越界
		ListUtils.CheckIndexInRange(0, size, index);
		if (size == elementData.length) {
			elementData = Arrays.copyOf(elementData, elementData.length + 10);
		}
		if (index < size) {
			for (int i = size; i > index; i--) {
				elementData[i] = elementData[i - 1];
			}
		}
		elementData[index] = o;
		size++;
	}

	@Override
	public Object remove(int index) {
		ListUtils.CheckIndexInRange(0, size - 1, index);
		Object tag = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		elementData[size - 1] = null;
		size--;
		return tag;

	}

	@Override
	public Object get(int index) {
		ListUtils.CheckIndexInRange(0, size - 1, index);
		return elementData[index];

	}

	@Override
	public int size() {
		return size;
	}

	public Iterator iterator() {
		return new Iterator() {
			private int cursor;

			@Override
			public boolean hasNext() {
				if (size > 0) {
					return cursor < size;
				} else {
					return false;
				}

			}

			@Override
			public Object next() {
				Object tag = get(cursor);
				cursor++;
				return tag;
			}
		};

	}

}
