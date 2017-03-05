package com.basic.datastructure;

public class ArrayList implements List {

	private Object[] elementData;
	private int size;

	private int enableCapacity;

	public ArrayList() {
		this.enableCapacity = 10;
		this.elementData = new Object[enableCapacity];
	}

	@Override
	public void add(Object o) {
		growIfNeeded();
		elementData[size] = o;
		this.size++;
	}

	@Override
	public void add(int index, Object o) {
		rangeCheckForAdd(index);
		growIfNeeded();

		Object[] tmpObjects = new Object[elementData.length];
		System.arraycopy(elementData, 0, tmpObjects, 0, index);
		tmpObjects[index] = o;
		System.arraycopy(elementData, index, tmpObjects, index + 1, elementData.length - index - 1);

		elementData = tmpObjects;

		this.size++;
	}

	@Override
	public Object get(int index) {
		if (index > size) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
		}
		return elementData[index];
	}

	@Override
	public Object remove(int index) {
		Object removedObj = this.get(index);
		rangeCheck(index);
		Object[] tmpObjects = new Object[elementData.length];

		System.arraycopy(elementData, 0, tmpObjects, 0, index);
		System.arraycopy(elementData, index + 1, tmpObjects, index, elementData.length - index - 1);

		elementData = tmpObjects;

		return removedObj;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public String toString() {
		for (int i = 0; i < elementData.length; i++) {
			System.out.print(elementData[i] + ",");
		}
		return "";
	}

	private void rangeCheck(int paramInt) {
		if ((paramInt < 0) || (paramInt >= size)) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(paramInt));
		}
	}

	private void rangeCheckForAdd(int paramInt) {
		if ((paramInt < 0) || (paramInt > size)) {
			throw new IndexOutOfBoundsException(outOfBoundsMsg(paramInt));
		}
	}

	private String outOfBoundsMsg(int paramInt) {
		return "Index: " + paramInt + ", Size: " + size;
	}

	private Object[] growIfNeeded() {
		if (enableCapacity <= this.size) {
			enableCapacity = enableCapacity * 2;
			Object[] largerElementData = new Object[enableCapacity];
			System.arraycopy(elementData, 0, largerElementData, 0, elementData.length);
			elementData = largerElementData;
		}
		return elementData;
	}

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		for (int i = 0; i <= 11; i++) {
			list.add(String.valueOf(i));
		}
		System.out.println(list.get(11));
		//list.add(10,"test");
		//list.get(10);
		//list.remove(10);
		//System.out.println(list);
	}

}
