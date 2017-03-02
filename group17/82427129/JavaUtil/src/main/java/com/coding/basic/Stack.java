package com.coding.basic;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		int length = size();
		Object lastData = peek();
		elementData.remove(length - 1);
		
		return lastData;
	}
	
	public Object peek(){
		if(size()<=0)
			throw new EmptyStackException();
		
		return elementData.get(size()-1);
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
