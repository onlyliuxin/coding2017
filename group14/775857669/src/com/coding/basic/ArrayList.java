package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private static final int MIN_EXTEND = 10;
	
	private Object[] elementData = new Object[100];
	/**
	 * 
	 * @Author: yuhe
	 * @Description: 确保数组的容量
	 * @param next 要插入的位置
	 */
	private void ensureCapacity(int capacity) {
		if (capacity < elementData.length) {
			return;
		} else {
			int newLength = capacity + MIN_EXTEND;
			elementData = Arrays.copyOf(elementData, newLength);
		}
		
	}
	
	private void rangeCheckForAdd(int index) {
		if (index < 0 || index > size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
	
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException(index);
		}
	}
	
	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		rangeCheckForAdd(index);
		ensureCapacity(size +1);
		System.arraycopy(elementData, index, elementData, index+1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object ret = elementData[index];
		int numToRemove = size-index-1;
		if (numToRemove > 0)
			System.arraycopy(elementData, index+1, elementData, index, numToRemove);
		elementData[size--] = null;
		return ret;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator() {
			private int index = 0;
			@Override
			public Object next() {
				return elementData[index++];
			}
			
			@Override
			public boolean hasNext() {
				return index < size;
			}
		};
	}
}
