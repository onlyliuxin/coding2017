package com.github.fei9009.coding2017.basic;


public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		Object o = elementData.remove(elementData.size() - 1);
		return o;
	}
	
	public Object peek(){
		Object o = elementData.get(elementData.size() - 1);
		return o;
	}
	public boolean isEmpty(){
		return elementData.size() == 0 ? true : false;
	}
	public int size(){
		return elementData.size();
	}
}
