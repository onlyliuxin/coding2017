package com.github.miniyk2012.coding2017.basic;

public class Stack {
	
	// 栈顶  《-》 1，2，3，4 栈底
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(0, o);
	}
	
	public Object pop(){
		return elementData.remove(0);
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
