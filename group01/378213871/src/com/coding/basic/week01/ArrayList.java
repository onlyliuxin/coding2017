package com.coding.basic.week01;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		ensureCapacity(size + 1);
		elementData[size++] = o;
	}
	
	public void ensureCapacity(int size) {
		if (size > elementData.length) {
			grow();
		}
	}
	
	public void grow() {
		elementData = Arrays.copyOf(elementData, size * 2);
	}
	
	public void add(int index, Object o){
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		ensureCapacity(size + 1);
		System.arraycopy(elementData, index, elementData, index + 1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elementData[index];
	}
	
	public Object remove(int index){
		if (index < 0 || index >= size) {
			throw new ArrayIndexOutOfBoundsException();
		}
		Object removedItem = elementData[index];
		System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
		size--;
		return removedItem;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new Iterator() {
			private int current = 0;
			
			public boolean hasNext() {
				return current < size();
			}
			
			public Object next() {
				if(!hasNext()) {
					throw new java.util.NoSuchElementException();
				}
				return elementData[current++];
			}
		};
	}
	
}
