package com.coding.basic.array;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[100];

	public void add(Object o) {
		size++;
		if(size == elementData.length){
			expansion();
		}
		elementData[size] = o;
	}

	public void add(int index, Object o) {
		if (index > size + 1 || index < 0) {
			throw new RuntimeException("index越界，不能大于size或小于0");
		}
		if (index >= elementData.length) {
			expansion();
			elementData[index] = o;
			size ++;
			return;
		}
		Object temp = elementData[index];
		elementData[index] = o;
		for (int i = index + 1; i < size; i++) {
			Object temp1 = elementData[i + 1];
			elementData[i] = temp;
			temp = temp1;
		}
		size ++;
	}

	public Object get(int index) {
		if (index > size + 1 || index < 0) {
			throw new RuntimeException("index越界，不能大于size或小于0");
		}
		return elementData[index];
	}

	public Object remove(int index) {
		return null;
	}

	public int size() {
		return size;
	}

	public Iterator iterator() {
		return null;
	}
	
	/**
	 * 对数组进行扩容
	 */
	private void expansion(){
		System.out.println("数组长度不够，扩容到" + (elementData.length + 100));
		Object[] e = new Object[elementData.length + 100];
		for (int i = 0; i < elementData.length; i++) {
			e[i] = elementData[i];
		}
		elementData = e;
	}

}
