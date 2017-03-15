package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData;
	
	private static Object[] EMPTY_ELEMENTDATA = {};
	
	private static int INITIALCAPACITY = 10;
	
	public ArrayList(){
		elementData = EMPTY_ELEMENTDATA;
	}
	
	public ArrayList(int initialCapacity){
		elementData = new Object[INITIALCAPACITY];
	}
	
	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		rangeCheckForAdd(index);
		
		ensureCapacity(size+1);
		System.arraycopy(elementData, index, elementData, index+1, size-index);
		elementData[index] = o;
		size++;
	}
	
	public Object set(int index, Object o){
		rangeCheck(index);
		
		Object oldValue = elementData[index];
		elementData[index] = o;
		return oldValue;
	}
	
	public Object get(int index){
		rangeCheck(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		Object oldValue = elementData[index];
		int movedLength = size - index - 1;
		if(movedLength > 0)//当要删除最后一个元素时，不需要移动数组，只需要把最后一个元素置null
			System.arraycopy(elementData, index+1, elementData, index, size-index-1);
		elementData[--size] = null;
		return oldValue;
	}
	
	private void rangeCheckForAdd(int index){
		if( index > size || index<0 ){
			throw new IndexOutOfBoundsException(outofIndex(index));
		}
	}
	private void rangeCheck(int index){
		if( index >= size || index < 0){
			throw new IndexOutOfBoundsException(outofIndex(index));
		}
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
	}
	
	private void ensureCapacity(int minCapacity){
		if(elementData == EMPTY_ELEMENTDATA){
			minCapacity = Math.max(minCapacity, INITIALCAPACITY);//针对addall首次增加的数量就比INITIALCAPACITY多
		}
		if(minCapacity - elementData.length > 0){
			grow(minCapacity);
		}
	}
	
	private void grow(int minCapcity){
		int oldCapacity = elementData.length;
		int newCapcity = oldCapacity + (oldCapacity>>1);
		if(newCapcity - minCapcity < 0){
			newCapcity = minCapcity;
		}
		elementData = Arrays.copyOf(elementData, newCapcity);
	}
	
	private String outofIndex(int index){
		return "Index: "+index+", Size: "+size;
	}
}
