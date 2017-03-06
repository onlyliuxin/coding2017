package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		if (size>=elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
		elementData[size] = o;
		size+=1;
	}
	public void add(int index, Object o){
		if (size>=elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length * 2);
		}
		System.arraycopy(elementData, index, 
				elementData, index+1, size-index);
		elementData[index]=o;
		size+=1;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		System.arraycopy(elementData, index+1, 
				elementData, index, size-index);
		size-=1;
		return elementData;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}