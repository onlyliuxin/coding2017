package com.coding.basic;
import com.coding.basic.exception.*;
public class Queue {

	private LinkedList elementData;

	public Queue() {
		elementData = new LinkedList();
	}

	public void enQueue(Object o){		
		elementData.addLast(o);		
	}
	
	public Object deQueue(){
		if (isEmpty()) {
			throw new EmptyQueueException("队空"); 
		}
		Object result = elementData.removeFirst();
		return result;
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
