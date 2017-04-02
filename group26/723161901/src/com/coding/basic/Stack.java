package com.coding.basic;

import java.util.Arrays;

public class Stack {
	// private ArrayList elementData = new ArrayList();

	private Object[] elementData = {};
	private int size;

	public enum CapaCityFlag {
		ADD, SUB
	}

	public void push(Object o) {
		addElement(o);
	}

	public Object pop() {
		Object obj = elementData[--size];
		grow(CapaCityFlag.SUB);
		return obj;
	}

	public Object peek() {
		// empty
		return elementData[size - 1];
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return size;
	}

	private void addElement(Object obj) {
		grow(CapaCityFlag.ADD);
		elementData[size++] = obj;
	}

	private void grow(CapaCityFlag capaCityFlag) {
		int oldElementData = elementData.length;
		if (CapaCityFlag.ADD.equals(capaCityFlag)) {
			oldElementData++;
		}
		if (CapaCityFlag.SUB.equals(capaCityFlag)) {
			oldElementData--;
		}
		elementData = Arrays.copyOf(elementData, oldElementData);
	}
}
