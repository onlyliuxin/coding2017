package com.coding.basic;

public class ArrayList implements List {

	public static void main(String[] args) {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(2,1);
		list.add(0,6);
		list.remove(0);
		list.remove(4);
		list.add(0,6);
		list.add(0,6);
		list.add(0,6);
		list.add(0,6);
		list.add(0,6);
		list.add(0,6);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		if (size == elementData.length) {
			grow();
		}
		elementData[size++] = o;
	}

	private void grow() {
		Object[] target = new Object[elementData.length + size];
		for (int i = 0; i < elementData.length; i++) {
			target[i] = elementData[i];
		}
		elementData = target;
	}

	public void add(int index, Object o) {
		if (size == elementData.length) {
			grow();
		}
		for (int i = size++; i > index; i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
	}

	public Object get(int index) {
		return elementData[index];
	}

	public Object remove(int index) {
		Object data = elementData[index];
		for (int i = index; i < size - 1; i++) {
			elementData[i] = elementData[i + 1];
		}
		size--;
		return data;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return null;
	}

}
