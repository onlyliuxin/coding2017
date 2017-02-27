package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if (fullCheck())
			elementData = Arrays.copyOf(elementData, size*2);
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		
		if (fullCheck())
			elementData = Arrays.copyOf(elementData, size*2);
		if (!rangeCheck(index))
			throw new IndexOutOfBoundsException();
		for (int i = size; i > index; --i) 
			elementData[i] = elementData[i-1];
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if (rangeCheck(index))
			return elementData[index];
		throw new IndexOutOfBoundsException();
	}
	
	public Object remove(int index){
		if (!rangeCheck(index))
			throw new IndexOutOfBoundsException();
		Object rmo = elementData[index];
		for (int i = index; i < size-1; ++i)
			elementData[i] = elementData[i-1];
		size--;
		return rmo;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private boolean rangeCheck(int index) {
		if (index < 0 || index >= size)
			return false;
		return true;
	}
	
	private boolean fullCheck() {
		if (size >= elementData.length)
			return true;
		return false;
	}
	
}
