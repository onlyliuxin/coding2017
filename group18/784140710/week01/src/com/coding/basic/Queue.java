package com.coding.basic;

public class Queue {
	private LinkedList list = new LinkedList();
	public void enQueue(Object o){	
		
		list.addLast(o);
	}
	
	public Object deQueue(){
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return list.removeFirst() == null ? true:false;
	}
	
	public int size(){
		return list.size();
	}
}
