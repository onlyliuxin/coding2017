package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		if (isEmpty()) {
			throw new IllegalStateException("the stack is empty");
		}
		return elementData.remove(elementData.size() - 1);
	}
	
	public Object peek(){
		if (isEmpty()) {
			throw new IllegalStateException("the stack is empty");
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
