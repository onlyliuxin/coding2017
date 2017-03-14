package com.coding.basic;

public class Queue {
	private LinkedList elementData = new LinkedList();

	public void enQueue(Object o){		
		elementData.addLast(o);
	}
	
	public Object deQueue(){
		if (isEmpty()) {
			return null;
		}else{
			return elementData.removeFirst();
		}
	}
	
	public boolean isEmpty(){
		if (elementData.size() == 0) {
			return true;
		}
		return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
