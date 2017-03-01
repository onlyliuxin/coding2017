package com.maple.basic;

public class Queue {
	private LinkedList elementData=new LinkedList();
	public void enQueue(Object o){		
		elementData.addLast(o);
	}
	
	public Object deQueue(){
		Object first=elementData.removeFirst();
		return first;
	}
	
	public boolean isEmpty(){
		return elementData.size()<=0;
	}
	
	public int size(){
		return elementData.size();
	}
}
