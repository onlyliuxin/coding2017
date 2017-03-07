package com.coding.week1;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		int top = elementData.size()-1;
		return elementData.remove(top);
	}
	
	public Object peek(){
		int top = elementData.size()-1;
		return elementData.get(top);
	}
	public boolean isEmpty(){
		if(elementData.size()==0){
			return true;
		}
		return false;
	}
	public int size(){
		return elementData.size();
	}
}
