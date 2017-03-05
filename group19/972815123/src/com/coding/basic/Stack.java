package com.coding.basic;

public class Stack {

private ArrayList elementData = new ArrayList();
private int size = 0;
	
	public void push(Object o){	
		elementData.add(o);
		size ++;
	}
	
	public Object pop(){
		if(size > 0){
			size--;
			return elementData.remove(size);
		}else{
			return null;
		}
	}
	
	public Object peek(){
		return elementData.get(size);
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
}
