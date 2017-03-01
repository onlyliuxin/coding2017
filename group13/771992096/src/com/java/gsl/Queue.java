package com.java.gsl;

public class Queue {
	LinkedList queue = new LinkedList();
	public void enQueue(Object o){		
		queue.add(o);
	}
	
	public Object deQueue(){
		return queue.removeLast();
	}
	
	public boolean isEmpty(){
		return queue.size() > 0;
	}
	
	public int size(){
		return queue.size();
	}
}
