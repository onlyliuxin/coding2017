package com.donaldy.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o) {
		elementData.add(o);
	}
	
	public Object pop() {
		return elementData.remove(size() - 1);
	}
	
	public Object peek() {
		return elementData.get(size() - 1);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size(){
		return elementData.size();
	}
}
