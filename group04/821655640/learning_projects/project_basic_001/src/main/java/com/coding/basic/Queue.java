package com.coding.basic;

public class Queue {
	
	private LinkedList elements = new LinkedList();
	
	public void enQueue(Object o){
		elements.addFirst(o);
	}
	
	public Object deQueue(){
		return elements.removeLast();
	}
	
	public boolean isEmpty(){
		return 0==elements.size();
	}
	
	public int size(){
		return elements.size();
	}
	
	@Override
	public String toString() {
		return elements.toString();
	}
}
