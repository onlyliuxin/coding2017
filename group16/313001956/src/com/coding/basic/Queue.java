package com.coding.basic;

public class Queue {
	
	private LinkedList elementData = new LinkedList();
	public void enQueue(Object o){
		elementData.addLast(o);
	}
	
	public Object deQueue(){
		Object o= elementData.get(0);
		elementData.removeFirst();
		return o;
	}
	
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
}
