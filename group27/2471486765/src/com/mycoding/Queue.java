package com.mycoding;

import java.util.NoSuchElementException;

public class Queue {

	private LinkedList linked = new LinkedList();
	
	public void enQueue(Object o) {		
		linked.add(o);
	}
	
	public Object deQueue() {
		
		if(isEmpty()) {
			throw new NoSuchElementException();
		}
		return linked.remove(size()-1);
	}
	
	public boolean isEmpty() {
		if(size()-1 == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int size() {
		return linked.size();
	}
	
	@Override
	public String toString() {
		return linked.toString();
	}
}
