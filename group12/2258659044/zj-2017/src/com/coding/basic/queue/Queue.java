package com.coding.basic.queue;

import com.coding.basic.linklist.LinkedList;

public class Queue<E> {
	
	private LinkedList<E> element = new LinkedList<E>();
	
	public void enQueue(E o){	
		
		element.add(o);
	}
	
	public E deQueue(){
		
		return element.removeFirst();
	}
	
	public boolean isEmpty(){
		
		return element.size()==0;
	}
	
	public int size(){
		
		return element.size();
	}
}
