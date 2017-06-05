package com.coding.basic.stack;

import com.coding.basic.array.ArrayList;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < elementData.size(); i++) {
			sb.append(elementData.get(i)).append(";");
		}
		return sb.toString();
	}
}
