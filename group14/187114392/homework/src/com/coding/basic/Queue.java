package com.coding.basic;

public class Queue {

	private LinkedList lnk = new LinkedList();
	
	public void enQueue(Object o){
		lnk.addLast(o);
	}
	
	public Object deQueue(){
		return lnk.removeFirst();
	}
	
	public boolean isEmpty(){
		return lnk.size() == 0;
	}
	
	public int size(){
		return lnk.size();
	}
}
