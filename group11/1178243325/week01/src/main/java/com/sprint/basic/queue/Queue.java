package com.sprint.basic.queue;
import com.sprint.basic.exception.EmptyQueueException;
import com.sprint.basic.list.LinkedList;
public class Queue {

	private LinkedList elementData;

	public Queue() {
		elementData = new LinkedList();
	}

	public boolean enQueue(Object o){		
		elementData.addLast(o);	
		return true;
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
