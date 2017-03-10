package com.coding.basic;

import java.util.Arrays;

/**
 * 自定义ArrayList
 * 
 * @author xiongrui233
 *
 */
public class ArrayList implements List {

	// list长度
	private int size = 0;

	// list的元素集合
	private Object[] elementData = new Object[10];

	/**
	 * 合并数组
	 * 
	 * @param arrays1
	 * @param arrays2
	 * @return Object[]
	 */
	private Object[] concat(Object[] arrays1, Object[] arrays2) {
		Object[] newArrays = new Object[arrays1.length + arrays2.length];
		System.arraycopy(arrays1, 0, newArrays, 0, arrays1.length);
		System.arraycopy(arrays2, 0, newArrays, arrays1.length, arrays2.length);
		return newArrays;
	}

	/**
	 * 分割数组
	 * 
	 * @param arrays
	 * @param from
	 * @param index
	 * @return Object[]
	 */
	private Object[] subArrays(Object[] arrays, int from, int index) {
		Object[] tempArrays = new Object[index - from];
		for (int i = from, j = 0; i < index; i++, j++) {
			tempArrays[j] = arrays[i];
		}
		return tempArrays;
	}

	/**
	 * 动态增长list长度
	 * 策略为:newSize = oldSize * 1.5
	 * 
	 * @param oldSize
	 */
	private void grow(int oldSize) {
		elementData = Arrays.copyOf(elementData, oldSize + oldSize / 2);
	}

	/**
	 * 检查在插入新元素时,list长度是否足够
	 * 
	 * @param newSize
	 */
	private void checkSize(int newSize) {
		int oldSize = elementData.length;
		if (newSize > oldSize) {
			grow(oldSize);
		}
	}

	/**
	 * 新增元素
	 * 
	 * @param Object
	 */
	public void add(Object o) {
		checkSize(size + 1);
		elementData[size++] = o;
	}

	/**
	 * 新增元素
	 * 
	 * @param index
	 * @param Object
	 */
	public void add(int index, Object o) {
		checkSize(size + 1);
		Object[] arrays1 = subArrays(elementData, 0, index);
		Object[] arrays2 = subArrays(elementData, index, elementData.length);

		arrays1 = Arrays.copyOf(arrays1, arrays1.length + 1);
		arrays1[index] = o;
		size++;
		elementData = concat(arrays1, arrays2);
	}

	/**
	 * 获得编号为index的元素
	 * 
	 * @param int
	 * @return Object
	 */
	public Object get(int index) {
		return elementData[index];
	}

	/**
	 * 删除编号为index的元素
	 * 
	 * @param int
	 * @return Object
	 */
	public Object remove(int index) {
		Object[] arrays1 = subArrays(elementData, 0, index);
		Object[] arrays2 = subArrays(elementData, index + 1, elementData.length);
		Object obj = elementData[index];

		size--;
		elementData = concat(arrays1, arrays2);
		return obj;
	}

	/**
	 * 返回list长度
	 * 
	 * @return int
	 */
	public int size() {
		return size;
	}

	/**
	 * 重写迭代器
	 *
	 * @return IteratorImpl
	 */
	public Iterator iterator() {

		class IteratorImpl implements Iterator {

			private int point = 0;

			@Override
			public boolean hasNext() {
				if (elementData[point] != null) {
					return true;
				}
				return false;
			}

			@Override
			public Object next() {
				return elementData[point++];
			}

		}
		return new IteratorImpl();
	}

	@Override
	public String toString() {
		return Arrays.toString(Arrays.copyOf(elementData, size));
	}
}