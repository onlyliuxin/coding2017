package com.coding.basic;

public class Queue {
	private LinkedList linkedList;

	public Queue() {
		linkedList = new LinkedList();
	}

	public void enQueue(Object o) {
		linkedList.add(o);
	}

	public Object deQueue() {
		return linkedList.removeFirst();
	}

	public boolean isEmpty() {
		return linkedList.size() == 0;
	}

	public int size() {
		return linkedList.size();
	}
}
