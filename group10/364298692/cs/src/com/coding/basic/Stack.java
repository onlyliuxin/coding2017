package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(elementData.size(), o);
	}
	
	public Object pop(){
		Object obj = elementData.remove(elementData.size()-1);
		return obj;
	}
	
	public Object peek(){
		Object obj = elementData.get(0);
		return obj;
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
}
