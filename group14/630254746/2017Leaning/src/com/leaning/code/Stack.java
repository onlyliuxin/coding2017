package com.leaning.code;

public class Stack {
	
	private ArrayList list = new ArrayList();
	
	private int size;
	
	
	public void push(Object o){
		list.add(o);
		size ++;
	}
	
	public Object pop(){
		return list.get(--size);
	}
	
	public Object peek(){
		return list.get(size-1);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
	
}
