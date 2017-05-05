package com.coding;

public class Queue<T> {
	
	private LinkedList<T> elementData = new LinkedList<T>();
	
	public void enQueue(T o){	
		elementData.addFirst(o);
	}
	
	public T deQueue(){
		return elementData.removeLast();
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
