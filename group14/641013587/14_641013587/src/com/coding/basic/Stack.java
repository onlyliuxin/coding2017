package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(0, o);
	}
	
	public Object pop(){
		Object object = elementData.get(0);
		elementData.remove(0);
		return object;
	}
	
	public Object peek(){
		return elementData.get(0);
	}
	public boolean isEmpty(){
		return elementData.size()==0?true:false;
	}
	public int size(){
		return elementData.size();
	}
}
