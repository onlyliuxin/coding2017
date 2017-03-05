package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
    private int size;  
    
	public void push(Object o){	
		elementData.add(o);
		size++;
	}
	
	public Object pop(){
		if(size>0){
			size--;
			return elementData.remove(size);
		}
		return null;
	}
	
	public Object peek(){
		if(size>0)
			elementData.get(size-1);
		return null;
	}
	public boolean isEmpty(){
		return size>0;
	}
	public int size(){
		return size;
	}
}
