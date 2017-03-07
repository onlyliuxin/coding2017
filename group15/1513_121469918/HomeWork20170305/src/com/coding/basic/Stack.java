package com.coding.basic;

import java.util.NoSuchElementException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size;
	
	public void push(Object o){
		elementData.add(o);
		size++;
	}
	
	public Object pop(){
		if(size<=0){
			throw new NoSuchElementException();
		}
		int len = size-1;
		Object val = elementData.remove(len);
		size--;
		return val;
	}
	
	public Object peek(){
		if(size<=0){
			throw new NoSuchElementException();
		}
		int len = size-1;
		return elementData.get(len);
	}
	public boolean isEmpty(){
		boolean flag = false;
		if(size >= 0){
			flag = true;
		}
		return flag;
	}
	public int size(){
		return size;
	}
}
