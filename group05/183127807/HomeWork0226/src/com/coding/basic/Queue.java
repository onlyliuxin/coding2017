package com.coding.basic;

public class Queue {
	private LinkedList linkedList = new LinkedList();
	
	public void enQueue(Object o){
		linkedList.addLast(o);
	}
	
	public Object deQueue(){
		Object removeQueue = linkedList.removeFirst();
		return removeQueue;
	}
	
	public boolean isEmpty(){
		return linkedList.head()==null;
	}
	
	public int size(){
		return linkedList.size();
	}
}
