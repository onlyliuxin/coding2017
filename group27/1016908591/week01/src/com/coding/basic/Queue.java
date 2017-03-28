package com.coding.basic;

public class Queue {
	private LinkedList List  = new LinkedList(); 
	
	public void enQueue(Object o){
		List.addLast(o);
	}
	
	public Object deQueue(){
		return List.removeFirst();
	}
	
	public boolean isEmpty(){
		return List.size()==0;
	}
	
	public int size(){
		return List.size();
	}
}
