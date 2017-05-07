package com.danny.hw1;

public class Queue {
	private LinkedList elementData = new LinkedList();

	public void enQueue(Object o){	
		elementData.add(o);
	}

	public Object deQueue(){
		if ( size() > 0 ) {
			return elementData.remove(0);
		} else {
			throw new IndexOutOfBoundsException("Queue is Null");
		}
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public int size(){
		return elementData.size();
	}
}
