package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o) {
		elementData.add(o);
	}
	
	public Object pop(){
		int length = elementData.size();
		if (length == 0) {
			return null;
		}
		return elementData.remove(length - 1);
	}
	
	public Object peek(){
		int length = elementData.size();
		if (length == 0) {
			return null;
		}
		return elementData.get(length - 1);
	}

	public boolean isEmpty(){
		if (elementData.size() != 0) {
			return false;
		} else {
			return true;
		}
	}

	public int size(){
		return elementData.size();
	}
}
