package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[5];
	
	public void add(Object o){
		if(++size > elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length*2);
		}
		elementData[size-1] = o;
		
	}
	public void add(int index, Object o){
		Object[] tmp = new Object[elementData.length];
		
		if(index<0 || index >elementData.length-1){
			return;
		}
		if(++size > elementData.length){
			elementData = Arrays.copyOf(elementData, elementData.length*2);
			tmp = new Object[elementData.length];
		}
		System.arraycopy(elementData, 0, tmp, 0, index);
		System.arraycopy(elementData, index, tmp, index+1, size-index);
		tmp[index] = o;
		elementData=tmp;
		
	}
	
	public Object get(int index){
		if(index<0 || index >elementData.length-1){
			return null;
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		Object o=null;
		o = elementData[index];
		if(--size%5 == 0){
			elementData = Arrays.copyOf(elementData, elementData.length/2);
		}else if(index == size-1){
			elementData[index] = null;
		}else if(index == size-1){
			
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		}
		
		return o;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayIterator(this);
	}
	
}



















