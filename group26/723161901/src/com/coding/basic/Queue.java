package com.coding.basic;

import java.util.Arrays;

public class Queue {

	private Object[] elementData = {};
	private int size;
	private int currCount;

	public enum CapaCityFlag {
		ADD, SUB
	}

	public Object enQueue(Object obj) {
		addElement(obj);
		return obj;
	}

	public Object deQueue() {
		Object obj = elementData[currCount++];
		grow(CapaCityFlag.SUB);
		return obj;
	}

	private void addElement(Object obj) {
		grow(CapaCityFlag.ADD);
		elementData[size++] = obj;
	}

	private void grow(CapaCityFlag capaCityFlag) {
		int oldElementData = elementData.length;
		if (CapaCityFlag.ADD.equals(capaCityFlag)) {
			oldElementData++;
			elementData = Arrays.copyOf(elementData, oldElementData);
		}
		if (CapaCityFlag.SUB.equals(capaCityFlag)) {
			oldElementData--;
			/*
			 * TODO 
			 * elementData = System.arraycopy(elementData, srcPos, dest, destPos, length)
					copyOf(elementData, oldElementData);*/
		}
	}

	public int size() {
		return size;
	}
}
