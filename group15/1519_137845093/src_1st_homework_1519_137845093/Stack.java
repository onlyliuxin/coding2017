package com.coding.basic;

import java.util.NoSuchElementException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size;
	private Object removeElement;
	public void push(Object o){
		elementData.add(o);
		size++;
	}
	
	public Object pop(){
		if(size<=0){
			throw new NoSuchElementException();
		}
		int l = size - 1;
		removeElement = elementData.remove(l);
		size--;	
		return removeElement;
	}
	
	public Object peek(){
		if(size<=0){
			throw new NoSuchElementException();
		}
		int len = size-1;
		return elementData.get(len);
	}
	
	public boolean isEmpty(){		 
		return (size>=0);
	}
	
	public int size(){
		return size;
	}
}
