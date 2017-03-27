package com.coding.basic;

public class Stack {
	private ArrayList list = new ArrayList();
	private int size=0;
	public void push(Object o){	
		list.add(o);
		size++;
	}
	
	public Object pop(){
		Object o=list.remove(size-1);
		size--;
		return o;
	}
	
	public Object peek(){
		Object o=list.get(size-1);
		return o;
	}
	public boolean isEmpty(){
		return size==0?true:false;
	}
	public int size(){
		return size;
	}
}
