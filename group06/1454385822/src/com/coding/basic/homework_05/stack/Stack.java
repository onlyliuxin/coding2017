package com.coding.basic.homework_05.stack;

import com.coding.basic.homework_01.ArrayList;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		if(!isEmpty()){
			return elementData.remove(elementData.size() - 1);
		}
		throw new RuntimeException("Stack is Empty");
	}
	
	public Object peek(){
		if(!isEmpty()){
			return elementData.get(elementData.size() - 1);
		}
		throw new RuntimeException("Stack is Empty");
	}
	
	public boolean isEmpty(){
		return elementData.size() > 0 ? false : true;
	}
	public int size(){
		return elementData.size();
	}
}
