package com.hmily.learning;

public class MyQueue {
	private MyArrayList elementData = new MyArrayList();
	private int size;
	public void enQueue(Object o){	
		elementData.add(o);
		size=elementData.size();
	}
	
	public Object deQueue(){
		Object o=elementData.remove(0);
		return o;
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return size;
	}
}
