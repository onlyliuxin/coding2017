package com.ace.coding;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;
	
	public void push(Object o){
		elementData.add(o);
		size++;
	}
	
	public Object pop(){
		checkStack();
		Object obj = elementData.remove(size()-1);
		size--;
		return obj;
	}
	
	public Object peek(){
		checkStack();
		return elementData.get(size()-1);
	}
	
	private void checkStack(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("This stack is empty");
		}
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
}
