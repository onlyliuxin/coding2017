package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		Object result = elementData.get(elementData.size()-1);
		elementData.remove(elementData.size()-1);
		return result;
	}
	
	public Object peek(){
		return  elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		if (elementData.size()==0){
			return true;
		}
		return false;
	}
	public int size(){
		return elementData.size();
	}
}
