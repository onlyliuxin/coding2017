package com.coding.basic;

public class Queue {
	
	private LinkedList queueList = new LinkedList();
	
	public void enQueue(Object o){		
		queueList.addFirst(o);
	}
	
	public Object deQueue(){
		return queueList.removeLast();
	}
	
	public boolean isEmpty(){
		return queueList.size() == 0;
	}
	
	public int size(){
		return queueList.size();
	}
}
