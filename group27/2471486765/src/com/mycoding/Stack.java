package com.mycoding;

import java.util.EmptyStackException;

public class Stack {
	
	private ArrayList elementData = new ArrayList();	

	public void push(Object o) {
		elementData.add(o);
	}

	public Object pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			elementData.remove(elementData.size()-1);
		}
		return elementData;
	}
	
	//只返回栈顶元素
	public Object peek() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		else {
			return elementData.get(elementData.size()-1);
		}		
	}
	
	//判断栈是否为空
	public boolean isEmpty() {
		if(elementData.size() == 0) {
			return true;
		} else {
			return false;
		}		
	}
	
	@Override
	public String toString() {	
		return elementData.toString();	
	}                                                       
	
}
