package org.coding.one;

public class Queue {
	
	private LinkedList dataElement = new LinkedList();
	
	public void enQueue(Object o){	
		dataElement.addLast(o);
	}
	
	public Object deQueue(){
		return dataElement.removeFirst();
	}
	
	public boolean isEmpty(){
		return dataElement.isEmpty();
	}
	
	public int size(){
		return dataElement.size();
	}
}
