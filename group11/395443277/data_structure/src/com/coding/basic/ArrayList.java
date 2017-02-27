package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[4];
	
	public void add(Object o){
		if (size == elementData.length) {
			// double size
			doubleSize();
		}
		
		elementData[size] = o;
		size++;
	}
	
	private void doubleSize() {
		elementData = Arrays.copyOf(elementData, elementData.length * 2);
	}
	
	public void add(int index, Object o){
		// check size
		if (size == elementData.length) {
			doubleSize();
		}
		
		//shift and add element
		System.arraycopy(elementData, index, elementData, index+1, size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		return elementData[index];
	}
	
	public Object remove(int index){
		if (size == 0) {
			return null;
		}
		
		// remove element and shift
		Object target = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index, size - index - 1);
		
		// reset last element
		elementData[size-1] = null;
		size--;
		return target;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator (){
		return new SeqIterator();
	}
	
	private class SeqIterator implements Iterator {
		int i = 0;
		
		@Override
		public boolean hasNext() {
			return i < size;
		}

		@Override
		public Object next() {
			if (!hasNext()) {
				return null;
			}
			return elementData[i++];
		}
		
	}
	
}
