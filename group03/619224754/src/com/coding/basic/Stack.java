package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		this.elementData.add(o);
	}
	
	public Object pop(){
		Object ret = this.elementData.remove(this.elementData.size() - 1);
		return ret;
	}
	
	public Object peek(){
		Object ret = this.elementData.get(this.elementData.size() - 1);
		return null;
	}
	public boolean isEmpty(){
		return this.elementData.size() == 0;
	}
	public int size(){
		return this.elementData.size();
	}
}
