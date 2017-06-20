package com.coding.basic;

import com.coding.basic.linklist.LinkedList;

public class Queue {

	private LinkedList elementData = new LinkedList();

	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.remove(0);
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
