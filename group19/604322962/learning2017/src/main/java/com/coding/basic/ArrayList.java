package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];

	private static final int GORW_CAPACITY = 10;
	public void add(Object o){
		if (size<elementData.length)
			elementData[size++] = o;
		else {
			elementData = Arrays.copyOf(elementData, elementData.length+GORW_CAPACITY);
			elementData[size++] = o;
		}
	}
	public void add(int index, Object o){
		checkIndex(index);
		System.arraycopy(elementData, index, elementData, index + 1,
				size - index);
		elementData[index] = o;
	}
	
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		checkIndex(index);
		Object obj = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index,
				size - index-1);
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}

	private void checkIndex(int index){
		if (index>elementData.length)
			throw new IndexOutOfBoundsException();
	}

}
