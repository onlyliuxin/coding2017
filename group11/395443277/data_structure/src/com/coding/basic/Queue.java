package com.coding.basic;

public class Queue {
	private LinkedList elementData = new LinkedList();
	
	public void enQueue(Object o){		
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
}
