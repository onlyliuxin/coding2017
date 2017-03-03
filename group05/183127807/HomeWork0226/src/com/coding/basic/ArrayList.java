package com.coding.basic;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList implements List {

	private static final int DEFAULT_CAPACITY = 100;

	private int size = 0;
	
	private Object[] elementData = new Object[DEFAULT_CAPACITY];
	
	public void add(Object o){
		ensureCapacity(size+1);
		elementData[size] = o;
		size++;
	}
	public void add(int index, Object o){
		if (index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		ensureCapacity(size+1);
		for (int i = size;i>index;i--) {
			elementData[i] = elementData[i - 1];
		}
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		if (index > size || index < 0) {
			throw new ArrayIndexOutOfBoundsException();
		}
		return elementData[index];
	}

	
	public Object remove(int index){
		 Object[] removeData = (Object[]) elementData[index];
		 for (int i =index;i<size()-1;i++) {
			 elementData[i] = elementData[i + 1];
		 }
		 elementData[size] = null;
		 size--;

		return removeData;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){

		return new ArrayListIterator();
	}

	private class ArrayListIterator implements Iterator {
		private int current = 0;
		@Override
		public boolean hasNext() {
			return current<size();
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return elementData[current++];
		}
	}


	public void ensureCapacity(int minCapacity) {
		if (minCapacity < elementData.length) {
			return;
		} else if (minCapacity > elementData.length) {

			elementData = Arrays.copyOf(elementData, minCapacity + DEFAULT_CAPACITY);
		}
	}
	
}
