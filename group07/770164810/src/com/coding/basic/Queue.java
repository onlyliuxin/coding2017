package com.coding.basic;

public class Queue {
	
	private LinkedList list = new LinkedList();
	
	public void enQueue(Object o){	
		list.addFirst(o);
	}
	
	public Object deQueue(){
		return list.removeLast();
	}
	
	public boolean isEmpty(){
		return list.size()==0;
	}
	
	public int size(){
		return list.size();
	}
}
