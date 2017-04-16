package com.liam.learn.code2017;

import java.lang.reflect.Array;
import java.util.Arrays;

public class ArrayList implements List {

	private int capacity = 10;
	private int size = 0;
	
	private Object[] elementData = null; //new Object[100];

	public ArrayList(){
		this.capacity = capacity;
		elementData = new Object[capacity];
	}

	public ArrayList(int custCapacity) {
		if(custCapacity <= 0){
			throw new IllegalArgumentException("Arraylist 长度不能为负数或0");
		}
		this.capacity = custCapacity;
		elementData = new Object[capacity];
	}

	public void add(Object o){
		if (size >= capacity){
			enlargeCapacity();
		}
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		if(index <0 || index >= size){
			throw new IllegalArgumentException("数组越界");
		}
		if (size >= capacity){
			enlargeCapacity();
		}
		System.arraycopy(elementData, index, elementData, index+1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if(index <0 || index >= size){
			throw new IllegalArgumentException("数组越界");
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		Object removedObj = get(index);

		int movedSize = size - (index + 1);
		if (movedSize > 0) {
			System.arraycopy(elementData, index+1, elementData, index, movedSize);
		}
		elementData[--size] = null;

		return removedObj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}


	@Override
	public String toString() {
		if (size < capacity){
			//int needRemove = capacity - size;
			Object[] toStringObj = new Object[size];
			System.arraycopy(elementData, 0, toStringObj, 0, size);
			return Arrays.toString(toStringObj);
		}
		return Arrays.toString(elementData);
	}

	private void enlargeCapacity(){
		capacity = capacity * 2;
		Object[] temp = new Object[capacity];
		System.arraycopy(elementData, 0, temp, 0, size);
		elementData = temp;
	}



}
