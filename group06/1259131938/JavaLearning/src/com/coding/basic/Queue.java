package com.coding.basic;

public class Queue {
	MyLinkedList elementData = new MyLinkedList();
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.remove(elementData.size());
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
