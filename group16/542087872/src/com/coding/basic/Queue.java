package com.coding.basic;

public class Queue {

	private LinkedList linkedList = new LinkedList();

	public void enQueue(Object o){
		linkedList.addLast(o);
	}
	
	public Object deQueue(){
		return linkedList.removeFirst();
	}
	
	public boolean isEmpty(){
		return linkedList.size() == 0;
	}
	
	public int size(){
		return linkedList.size();
	}
}
