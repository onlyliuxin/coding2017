package com.coding.basic;

import java.util.*;

public class ArrayList implements List {
	private int size = 0;
	private static final int INCREMENT = 10;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		growLength();
		elementData[size++] = o;
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
	
	public Object remove(int index) {
	    //如果之前分配了很多内存，多次remove后是否需要将elementData手动缩小容量
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
