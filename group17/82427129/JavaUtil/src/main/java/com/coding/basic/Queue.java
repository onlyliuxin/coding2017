package com.coding.basic;

public class Queue {
	private LinkedList elementData = new LinkedList();
	
	public void enQueue(Object o){
		elementData.addLast(o);
	}
	
	public Object deQueue(){
		return elementData.removeLast();
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
