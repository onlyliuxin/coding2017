package com.github.hwjcc969.coding2017.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	
	public void push(Object o){
		if (elementData.size() <= 99)
			elementData.add(o);
		size++;
		
	}
	
	public Object pop(){
		size--;
		return elementData.remove(elementData.size() - 1);

	}
	
	public Object peek(){
		return elementData.get(elementData.size() - 1);
	}
	public boolean isEmpty(){
		if (size == 0)
			return false;
		return true;
	}
	public int size(){
		return size;
	}
}
