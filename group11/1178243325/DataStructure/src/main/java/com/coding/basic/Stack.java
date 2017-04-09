package com.coding.basic;

import com.coding.basic.exception.*;
public class Stack {

	private ArrayList elementData;
	public Stack() {
		elementData = new ArrayList();
	}

	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		if (isEmpty()) {
			throw new EmptyStackException("栈空");
		}
		Object result = elementData.get(size()-1);
		elementData.remove(size()-1);
		return result;
	}
	
	public Object peek(){
		if (isEmpty()) {
			throw new EmptyStackException("栈空"); 
		}
		return elementData.get(0);
	}

	public boolean isEmpty(){
		return elementData.size() == 0;
	}

	public int size(){
		return elementData.size();
	}
}
