package com.team6.week1;

public class ArrayList implements List {

	int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		
		if (size >= elementData.length) {
			Object[] tem = new Object[elementData.length * 2];
			for (int i = 0; i < size; i++) {
				tem[i] = elementData[i];
			}
			elementData = tem;
		}
		elementData[size] = o;
		size++;
	}

	public void add(int index, Object o) {
		if (size >= elementData.length) {
			Object[] tem = new Object[elementData.length * 2];
			for (int i = 0; i < size; i++) {
				tem[i] = elementData[i];
			}
			elementData = tem;
		}
		for (int i = index; i < size; i++) {
			elementData[i + 1] = elementData[i];
		}
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		return elementData[index];
	}

	public Object remove(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		for (int i = index; i < size; i++) {
			elementData[i - 1] = elementData[i];
		}
		size--;
		return elementData[index];
	}

	public int size() {
		return size;

	}

}
