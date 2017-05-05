package com.donaldy.basic;

public class Queue {

	private LinkedList linkedList = new LinkedList();

	public void enQueue(Object o) {
		linkedList.add(o);
	}
	
	public Object deQueue() {
		return linkedList.remove(0);
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return linkedList.size();
	}
}
