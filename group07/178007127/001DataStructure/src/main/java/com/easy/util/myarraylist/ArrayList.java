package com.easy.util.myarraylist;

public class ArrayList {

	private int size = 0;

	private Object[] elementData;

	public ArrayList() {
		this.elementData = new Object[] {};
	}

	public ArrayList(int initialCapacity) {
		this.elementData = new Object[initialCapacity];
	}

	public void add(Object o) {
		if (elementData.length <= size) {
			grow(1);
			elementData[size] = o;
			size++;
		} else {
			elementData[size] = o;
			size++;
		}
	}

	public void add(int index, Object o) {
		rangeCheckForAdd(index);
		grow(1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}

	public Object get(int index) {
		rangeCheck(index);
		return elementData[index];
	}

	public Object remove(int index) {
		if (index < size) {
			Object removeValue = elementData[index];
			Object[] dest = new Object[size - 1];
			System.arraycopy(elementData, 0, dest, 0, index);
			System.arraycopy(elementData, index + 1, dest, index, size - index - 1);
			elementData = dest;
			size--;
			return removeValue;
		} else {
			return null;
		}
	}

	public int size() {
		return this.size;
	}

	private void grow(int minCapacity) {
		Object[] dest = new Object[elementData.length + minCapacity];
		System.arraycopy(elementData, 0, dest, 0, elementData.length);
		elementData = dest;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Object object : elementData) {
			sb.append(object + ",");
		}
		String temp = sb.toString();
		temp = temp.substring(0, temp.length() - 1);
		return "[" + temp + "]";
	}

	private void rangeCheck(int index) {
		if (index >= size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}

	private String outOfBoundsMsg(int index) {
		return "Index:" + index + ",Size:" + size;
	}

	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
		}
	}
}
