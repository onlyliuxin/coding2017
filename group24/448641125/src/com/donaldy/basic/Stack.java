package com.donaldy.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o) {
		this.elementData.add(o);
	}
	
	public Object pop() {
		return this.elementData.remove(size() - 1);
	}
	
	public Object peek() {
		return this.elementData.get(size() - 1);
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size(){
		return this.elementData.size();
	}
}
