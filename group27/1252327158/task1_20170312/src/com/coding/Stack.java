package com.coding;

public class Stack<T> {
	
	private ArrayList<T> elementData = new ArrayList<T>();
	
	public void push(T o){	
		elementData.add(o);
	}
	
	public T pop(){
		return elementData.remove(elementData.size() - 1);
	}
	
	public T peek(){
		return elementData.get(elementData.size() - 1);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
