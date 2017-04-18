package com.coding.basic;

public class Stack {
	private List elementData = new ArrayList();
	private int size = 0;
	public void push(Object o){		
		elementData.add(o);
		size ++;
	}
	
	public Object pop(){
		if (size == 0)
			return null;
		return elementData.remove(--size);
	}
	
	public Object peek(){
		if (size == 0)
			return null;
		return elementData.get(size - 1);
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
}
