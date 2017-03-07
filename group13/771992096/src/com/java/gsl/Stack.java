package com.java.gsl;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		int i = elementData.size();
		elementData.remove(i);
		return elementData.get(i);
	}
	
	public Object peek(){
		int i = elementData.size();
		return elementData.get(i);
	}
	public boolean isEmpty(){
		
		return elementData.size()>0;
	}
	public int size(){
		return elementData.size();
	}
}
