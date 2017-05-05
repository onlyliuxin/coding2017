package com.github.miniyk2012.coding2017.basic;

import java.util.Arrays;


public class ArrayList implements List {
	
	private int size = 0;
	private int DEFAULT_LENGTH = 10;
	
	private Object[] elementData = new Object[DEFAULT_LENGTH];
	
	public void add(Object o){
		ensureCapcacity();
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		ensurnPosition(index);
		ensureCapcacity();
		System.arraycopy(elementData, index, elementData, index + 1,
                size - index);
		elementData[index] = o;
		size++;
	}
	
	public Object get(int index){
		ensureIndex(index);
		return elementData[index];
	}
	
	public Object remove(int index){
		ensureIndex(index);
		Object temp = elementData[index];
		System.arraycopy(elementData, index+1, elementData, index,
                size - index - 1);
		elementData[size-1] = null;
		size--;
		return temp;
	}
	
	public void clear() {
		elementData = new Object[DEFAULT_LENGTH];
		size = 0;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		Iterator iterator = new IteratorImp(this);
		return iterator;
	}
	
	private void ensureCapcacity() {
		int oldLength = elementData.length;
		if (size+1 > oldLength) {
			elementData = Arrays.copyOf(elementData, 2*oldLength);
		}
	}
	
	private void ensureIndex(int index) {
		if (index >= size || index < 0) 
			throw new ArrayIndexOutOfBoundsException(String.format("index %d out of bounds [0-%d)", index, size));
	}
	
	private void ensurnPosition(int index) {
		if (index <0 || index>size)
			throw new ArrayIndexOutOfBoundsException(String.format("position %d out of position [0-%d)", index, size));
	}
	
	@Override 
	public String toString() {
		Object[] tempArray = Arrays.copyOf(elementData, size);
		return Arrays.toString(tempArray);
	}
	
}
