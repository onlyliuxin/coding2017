package com.coding.basic;

import java.util.NoSuchElementException;

public class ArrayList implements List {

	
	private int size = 0;
	private static final int DEFAULT_SIZE = 100;
	private Object[] elementData = new Object[DEFAULT_SIZE];
	
	//添加元素
	public void add(Object o){
		add(size(),o);
	}
	

	public void add(int index, Object o){
		if(elementData.length==size()){
			ensureCapacity(size()*2 + 1);
		}
		for (int i = size; i > index; i--) 
			elementData[i]=elementData[i-1];
			elementData[index] = o;
			size++;
	}
	//扩容
	public void ensureCapacity(int newCapacity){
		if(newCapacity < size){
			return;
		}
		Object[] oldElements =  elementData;
		elementData = new Object[newCapacity];
		for (int i = 0; i < size; i++) {
			elementData[i] = oldElements[i];
		}
	}
	//返回固定下标的元素
	public Object get(int index){
		if(index<0 || index >=size){
			throw new ArrayIndexOutOfBoundsException("指定的index超过界限");
		}
		return elementData[index];
	}
	//删除指定位置的元素
	public Object remove(int index){
		Object removeElement = elementData[index];
		for (int i = index; i < size; i++) {
			elementData[i] = elementData[i+1];
		}
		size--;
		return removeElement;
	}
	
	public int size(){
		return size;
	}
	
	public Iterator iterator(){
		return new ArrayListIterator();
	}
	
	private class ArrayListIterator implements  Iterator{
		private int current = 0;
		@Override
		public boolean hasNext() {
			return current < size;
		}
		@Override
		public Object next() {
			if(!hasNext()){
				 throw new NoSuchElementException();
			}
			return elementData[current+1];
		}
	}
	
}
