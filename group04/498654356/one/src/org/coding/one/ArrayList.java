package org.coding.one;

import java.util.Arrays;

public class ArrayList implements List {

	/**
	 * 用于存放数据的数组
	 */
	private Object[] elementData;
	
	/**
	 * 记录 ArrayList 中存放元素的个数
	 */
	private int size;
	
	/**
	 * ArrayList 的默认的初始容量值
	 */
	private static final int DEFAULT_CAPACITY = 10;
	
	public ArrayList() {
		this.elementData = new Object[DEFAULT_CAPACITY];
	}
	
	@Override
	public void add(Object o) {
		ensureGrow();
		this.elementData[size++] = o;
	}

	private void grow(int capacity) {
		this.elementData = Arrays.copyOf(this.elementData, capacity);
	}

	@Override
	public void add(int index, Object o) {
		checkRangeIndex(index);
		ensureGrow();
		System.arraycopy(this.elementData, index, this.elementData, index + 1, this.size - index);
		this.elementData[index] = o;
		this.size ++;
	}

	private void ensureGrow() {
		if(this.size == this.elementData.length) { //扩容
			grow(this.size + 1);
		}
	}

	@Override
	public Object get(int index) {
		checkGetValIndex(index);
		return this.elementData[index];
	}

	private void checkGetValIndex(int index) {
		if(index < 0 || index >= this.size) { //越界
			 throw new IndexOutOfBoundsException("Index: "+index+", Size: "+ this.size);
		}
		
	}

	private void checkRangeIndex(int index) {
		if(index < 0 || index > this.size) { //越界
			 throw new IndexOutOfBoundsException("Index: "+index+", Size: "+ this.size);
		}
	}

	@Override
	public Object remove(int index) {
		checkGetValIndex(index);
		Object oldVal = this.elementData[index];
		this.elementData[index] = null;	//释放引用
		System.arraycopy(this.elementData, index + 1, this.elementData, index, this.size - index -1);
		this.size--;
		return oldVal;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public boolean isEmpty() {
		return this.size == 0;
	}

	@Override
	public String toString() {
		return "ArrayList [elementData=" + Arrays.toString(elementData) + ", size=" + size + "]";
	}

}
