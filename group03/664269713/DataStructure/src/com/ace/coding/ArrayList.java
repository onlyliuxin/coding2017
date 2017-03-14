package com.ace.coding;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		checkArrayLength();
		elementData[size++] = o;
	}
	
	private void checkArrayLength(){
		if(elementData.length < size() + 1){
			// expand the origin length of the array
			int newLength = size * 2 + 1;
			elementData = Arrays.copyOf(elementData, newLength);
		}
	}
	
	private void checkIndex(int index){
		if(index < 0 || index >= size()){
			throw new IndexOutOfBoundsException("Index " + index + " is invalid.");
		}
	}
	
	public void add(int index, Object o){
		checkIndex(index);
		checkArrayLength();
		System.arraycopy(elementData, index, elementData, index+1, size() - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		checkIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		checkIndex(index);
		Object obj = elementData[index];
		if(index == size() - 1){
			elementData[index] = null;
		} else {
			System.arraycopy(elementData, index + 1, elementData, index, size() - index - 1);
		}
		size--;
		return obj;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return null;
//		return new ListIterator();
	}
	
	/*private class ListIterator implements Iterator{
		private int index = 0;
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return index != size;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			if(index >= size){
				throw new IndexOutOfBoundsException("There's no next element.");
			}
			return elementData[index++];
		}
		
	}*/
	
}
