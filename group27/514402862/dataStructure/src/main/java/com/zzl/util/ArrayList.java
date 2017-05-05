package com.zzl.util;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List{
	
	private static final int DEFAULT_SIZE = 4;
	
	private int size = 0;
	private Object[] elementData = new Object[DEFAULT_SIZE];
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	
	public void add(int index, Object o){
		rangeCheck(index);
		
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index+1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		rangeCheck(index);
		
		return elementData[index];
	}
	
	public Object remove(int index){
		rangeCheck(index);
		
		Object oldValue = elementData[index];
		
		int moveNum = size - index - 1;
		if(moveNum > 0)
			System.arraycopy(elementData, index+1, elementData, index, moveNum);
		
		elementData[--size] = null;
		return oldValue;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private void rangeCheck(int index){
		if(index > size || index < 0)
			throw new IndexOutOfBoundsException("Index:" + index + "size:" + size);
	}
	
	private void ensureCapacity(int minCapacity){
		if(minCapacity <= DEFAULT_SIZE){
			minCapacity = DEFAULT_SIZE;
		}
		
		if(minCapacity - elementData.length > 0)
			grow(minCapacity);
	}
	
	private void grow(int minCapacity){
		int oldSize = elementData.length;
		int newSize = oldSize + DEFAULT_SIZE;
		elementData = Arrays.copyOf(elementData, newSize);
	}
	
	private class ArrayListIterator implements Iterator{
		int cursor;
		
		@Override
		public boolean hasNext() {
			return cursor < size;
		}

		@Override
		public Object next() {
            if (hasNext()){
    			return elementData[cursor++];
    		}
            throw new NoSuchElementException();
		}
		
	}
}
