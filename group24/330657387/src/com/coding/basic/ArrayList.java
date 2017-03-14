package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[10];
	
    public void ensureCapacity(int input) {
        if (input > elementData.length) {
            growCapacity();
        }
    }
    
    private void growCapacity(){
    	elementData = Arrays.copyOf(elementData, size * 2);
    }
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	
	
	public void add(int index, Object o){
		rangeCheck(index);
		ensureCapacity(size + 1);
		System.arraycopy(elementData,index, elementData, index + 1, size - index);
		elementData[index] = o;
		size ++;
	}
	
	private void rangeCheck(int index){
		if (index > size || index < 0){
			throw new IndexOutOfBoundsException();
		}
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object dest = elementData[index];
		System.arraycopy(elementData, index +1, elementData, index, size-index-1);
		size --;
		return dest;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
