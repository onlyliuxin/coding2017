package com.coding.basic;

import java.util.Arrays;

public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[8];

	public int test = 10;
	
	public void add(Object o){
		if(size + 1 >elementData.length){
			expand();
		}
		elementData[size] = o;
		size++;
	}
	/**
	 *  Parameters:
	 *	index index at which the specified element is to be inserted
	 *	element element to be inserted
	 *	Throws:
	 *	IndexOutOfBoundsException - if the index is out of range (index < 0 || index > size())
	 */
	
	public void add(int index, Object o){
		if(index <0 || index > size()) throw new IndexOutOfBoundsException(index + ": Invalid Index");
		if(size()+1>elementData.length){
			expand();
		}
		if(index < size())
			System.arraycopy(elementData, index, elementData, index+1, size() - index);
		elementData[index] = o;
		size ++;
	}
	
	public Object get(int index){
		if(index <0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index + ": Invalid Index.");
		return elementData[index];
	}
	
	public Object remove(int index) {
		if(index <0 || index >= size()) throw new ArrayIndexOutOfBoundsException(index + ": Invalid Index.");
		Object item = elementData[index];
		if(index<size())
			System.arraycopy(elementData, index, elementData, index-1, size-index);
		return item;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterImpl(this);
	}
	
	private void expand(){
		Object[] newArray = new Object[elementData.length * 2];
		System.arraycopy(elementData, 0, newArray, 0, size);
		elementData = newArray;
	}

	private class ArrayListIterImpl implements Iterator{

		ArrayList al = null;
		public ArrayListIterImpl(ArrayList al){
			this.al = al;
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public Object next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
}
