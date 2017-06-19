package com.coding.basic.array;

import com.coding.basic.Iterator;
import com.coding.basic.List;

public class ArrayList implements List {

	private int size = 0;

	private Object[] elementData = new Object[10];

	public boolean add(Object o) {
		return this.add(this.size + 1, o);
	}

	public boolean add(int index, Object o) {
		if (index > size + 1 || index < 0) {
			throw new RuntimeException("index越界，不能大于size或小于0");
		}
		expansion(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
		return true;
	}

	public Object get(int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException("index越界，不能大于size或小于0");
		}
		return elementData[index];
	}

	public Object remove(int index) {
		if (index >= size || index < 0) {
			throw new RuntimeException("index越界，不能大于size或小于0");
		}
		Object result = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return result;
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
	private void expansion(int size) {
		if (size >= elementData.length) {
			System.out.println("数组长度不够，扩容到" + (elementData.length + (elementData.length / 2)));
			Object[] e = new Object[elementData.length + (elementData.length / 2)];
			for (int i = 0; i < elementData.length; i++) {
				e[i] = elementData[i];
			}
			elementData = e;
		}
	}

}
