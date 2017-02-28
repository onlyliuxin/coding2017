package com.guodong.datastructure;

public class Queue {
	private LinkedList element = new LinkedList();

	public void enQueue(Object o) {
		element.addLast(o);
	}

	public Object deQueue() {
		return element.removeFirst();
	}

	public boolean isEmpty() {
		return element.size() == 0;
	}

	public int size() {
		return element.size();
	}
}
