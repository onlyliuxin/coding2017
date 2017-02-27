package com.github.Ven13.coding2017.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		

		elementData.add(o);
	}
	
	public Object pop(){
		Object o = null;
		if(elementData.size() > 0) {
			o = elementData.get(elementData.size() - 1);
			elementData.remove(elementData.size() - 1);
		}
		return o;
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
