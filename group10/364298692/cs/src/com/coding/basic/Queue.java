package com.coding.basic;

public class Queue {
	
	private LinkedList linkedList;
		
	public void enQueue(Object o){
		linkedList.addLast(o);
	}
	
	public Object deQueue(){
		Object obj = linkedList.removeFirst();
		return obj;
	}
	
	public boolean isEmpty(){
		return linkedList.size() == 0;
	}
	
	public int size(){
		return linkedList.size();
	}
}
