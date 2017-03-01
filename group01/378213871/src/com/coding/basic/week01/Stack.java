package com.coding.basic.week01;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	//入栈
	public void push(Object o){	
		elementData.add(o);
		
	}
	
	//出栈
	public Object pop(){
		if(elementData.size() == 0) {
			throw new EmptyStackException();
		}
		return elementData.remove(elementData.size() - 1);
	}
	
	//读取栈顶元素
	public Object peek(){
		if(elementData.size() == 0) {
			throw new EmptyStackException();
		}
		return elementData.get(elementData.size() - 1);
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
