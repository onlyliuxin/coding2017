package com.coding.DataStructure;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public abstract class ArrayList implements List {
	
	private int size = 0;
	private static final int INCREMENT = 10;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o) {
		
		if ((size + 1)>= 100) {
			throw new RuntimeException("数组越界异常");
		}
		elementData[size] = 0;
		size++;
	}
	
	public void add(int index, Object o){
		growLength();
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;	
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index > size) throw new IndexOutOfBoundsException("index: "+index+", size: "+size);
			Object retVal = elementData[index];
			System.arraycopy(elementData, index+1, elementData, index, size-(index+1));
			elementData[--size] = null;
			return retVal;
	}
	
	public int size(){
		return this.size;
	}
	
	public Iterator iterator(){
		return null;
	}
	private void growLength() {
	    if (this.size == elementData.length) {
            elementData = Arrays.copyOf(elementData, elementData.length+INCREMENT);
        }
	}
}
