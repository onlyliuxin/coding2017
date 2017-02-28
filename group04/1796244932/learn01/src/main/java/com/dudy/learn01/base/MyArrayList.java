package com.dudy.learn01.base;

import java.util.Arrays;

public class MyArrayList implements MyList {

	private int size = 0;

	private Object[] elementData = new Object[16];

	/**
	 * 增加元素: ①数组没满之前，直接添加到最后，满了扩容添加
	 */
	public void add(Object o) {
		// 检查是否需要扩容
		this.checkGrow(size + 1);
		elementData[size++] = o;
	}

	/**
	 * 检查是否需要扩容
	 * 
	 * @param newSize
	 */
	private void checkGrow(int newSize) {
		if (newSize > elementData.length) {
			this.grow(elementData);
		}
	}

	/**
	 * 扩容
	 * 
	 * @param oldElementData
	 */
	private void grow(Object[] oldElementData) {
		int lenth = (int) (oldElementData.length * 1.5);
		elementData = Arrays.copyOf(oldElementData, lenth);
	}

	/**
	 * 根据索引添加:①同 add ② 可能会出现 index 超出当前位置的情况 ③往 中间插入时需要移位
	 */
	public void add(int index, Object o) {
		// 检查是否需要扩容
		if (index > size || index < 0) {
			throw new RuntimeException("Index: " + index + ", Size: " + size);
		}
		this.checkGrow(size + 1);
		// 循环移位
		int tmp = size;
		for (int i = 0; i < size - index; i++) {
			elementData[tmp] = elementData[tmp - 1];
			tmp--;
		}
		// 索引位置赋值
		elementData[index] = o;
		size++;
	}

	/**
	 * 直接返回相应数组下标就好
	 */
	public Object get(int index) {
		return elementData[index];
	}

	/**
	 * 删除元素:①注意移位
	 */
	public Object remove(int index) {
		// 检查是否需要扩容
		if (index > size || index < 0) {
			throw new RuntimeException("Index: " + index + ", Size: " + size);
		}
		Object desc = elementData[index];

		for (int i = 0; i < size - index; i++) {
			elementData[index] = elementData[index+1];
			index++;
		}
		size--;
		return desc;
	}

	public int size() {
		return this.size;
	}

	public MyIterator iterator() {


		return new MyItr();
	}

	public  class  MyItr implements  MyIterator{

		int cursor;



		public boolean hasNext() {
			return  cursor != size;
		}

		public Object next() {

			return elementData[cursor++];
		}
	}



	@Override
	public String toString() {
		StringBuffer str = new StringBuffer();
		str.append("[");
		for (int i = 0; i < size; i++) {
			str.append(elementData[i]+",");
		}
		str.append("]");
		return str.toString();
	}
}