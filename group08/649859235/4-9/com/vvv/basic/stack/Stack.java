package com.vvv.basic.stack;

import java.util.ArrayList;

public class Stack {
	private ArrayList<Object> elementData = new ArrayList<Object>();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop() {
		if (elementData.size() > 0) {
			return elementData.remove(elementData.size() - 1);
		}
		return null;
	}
	
	public Object peek() {
		if (elementData.size() > 0) {
			return elementData.get(elementData.size() - 1);
		}
		return null;
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}