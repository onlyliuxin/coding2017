package com.coding.basic;

public class Queue {

	LinkedList que = new LinkedList();

	public void enQueue(Object o){
		que.addLast(o);
	}
	
	public Object deQueue(){
	    return que.removeFirst();
	}

	
	public boolean isEmpty(){
		return que.size() == 0;
	}
	
	public int size(){
		return que.size();
	}
}
