package com.coding.basic;

public class Queue {
	LinkedList a = new LinkedList();
	public void enQueue(Object o){
		a.add(o);
	}
	
	public Object deQueue(){
		return a.removeFirst();
	}
	
	public boolean isEmpty(){
		if(a.size()==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return a.size();
	}
}
