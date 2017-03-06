package com.coding.basic.list;

public class Queue {
	
	private LinkedList linkedList = new LinkedList();
	
	public void enQueue(Object o){	
		linkedList.addFirst(o);
	}
	
	public Object deQueue(){
		return linkedList.removeLast();
	}
	
	public boolean isEmpty(){
		return linkedList.size() == 0;
	}
	
	public int size(){
		return linkedList.size();
	}
}
