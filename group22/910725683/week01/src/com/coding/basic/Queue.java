package com.coding.basic;

public class Queue {
	
	private LinkedList queue = new LinkedList();
	
	public void enQueue(Object o){
		queue.add(o);
	}
	
	public Object deQueue(){
		return queue.removeFirst();
	}
	
	public boolean isEmpty(){
		return queue.size()==0;
	}
	
	public int size(){
		return queue.size();
	}
	
	public String toString() {
		return queue.toString();
 	}
}