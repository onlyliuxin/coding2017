package com.sx.structures;

public class MyQueue {
	private MyLinkedList elements ;
	public MyQueue() {
		elements = new MyLinkedList();
	}
	
	public void enQueue(Object o){
		elements.add(o);
	}
	
	public Object deQueue(){
		return elements.removeFirst();
	}
	
	public boolean isEmpty(){
		if(size()>0)
			return false;
		return true;
	}
	public int size(){
		return elements.size();
	}
}
