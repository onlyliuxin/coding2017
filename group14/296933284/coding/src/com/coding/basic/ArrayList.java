package com.coding.basic;

import java.util.Arrays;

/**
 * ArrayList 实现 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class ArrayList implements List {

	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	private Object[] elementData;
	

	public ArrayList() {
		elementData = new Object[DEFAULT_CAPACITY];
	}

	public ArrayList(int initCapacity) {
		elementData = new Object[initCapacity];
	}

	/**
	 * 在数组末尾添加指定元素，若数组已满，则自动扩展为原来长度的两倍
	 */
	public void add(Object obj) {
		
		ensureCapacityInternal(size);

		elementData[size] = obj;
		size++;
	}
	

	/**
	 * 在数组的指定位置插入元素
	 */
	public void add(int index, Object obj) {

		rangCheckForAdd(index);
		ensureCapacityInternal(size + 1);
		
		for (int i = size - 1; i >= index; i--)
			elementData[i + 1] = elementData[i];

		elementData[index] = obj;
		size++;
	}

	/**
	 * 给数组扩容
	 */
	private void ensureCapacityInternal(int minCapacity) {
		if (minCapacity - elementData.length > 0) {
			int newCapacity = elementData.length * 2;
			elementData = Arrays.copyOf(elementData, newCapacity);
			// elementData = tempElementData;
		}
	}
	
	/**
	 * 用于在 add() 中检查数组下表是否越界
	 */
	private void rangCheckForAdd(int index) {
		if (index > size || index < 0)
			throw new IndexOutOfBoundsException();
	}

	/**
	 * 返回指定索引位置的元素值
	 */
	public Object get(int index) {

		rangCheck(index);

		return elementData[index];
	}

	/**
	 * 删除指定索引位置的元素，并返回该值
	 */
	public Object remove(int index) {
		rangCheck(index);

		Object obj = elementData[index];

		for (int i = index; i < size; i++)
			elementData[i] = elementData[i + 1];

		size--;

		return obj;
	}

	/**
	 * 检查数组下表是否越界
	 * 
	 * @param index
	 */
	private void rangCheck(int index) {
		if (index >= size)
			throw new IndexOutOfBoundsException();
	}

	/**
	 * 返回数组长度
	 */
	public int size() {
		return size;
	}
	
	/**
	 * 迭代器
	 * 
	 * @return
	 */
	public Iterator iterator() {
		return new Iter();
	}
	
	//迭代器内部类
	private class Iter implements Iterator {
		int current;
		
		@Override
		public boolean hasNext() {
			return current != size;
		}

		@Override
		public Object next() {
			
			int i = current;
			rangCheck(i);
			current++;
			
			return elementData[i];
		}
		
	}

}


