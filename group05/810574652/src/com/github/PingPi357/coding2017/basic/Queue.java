package com.github.PingPi357.coding2017.basic;

public class Queue {
	private LinkedList linkedList = new LinkedList();

	public void enQueue(Object o) {
		linkedList.addLast(o);
	}

	public Object deQueue() {
		Object removeQueue = linkedList.removeFirst();
		return removeQueue;
	}

	public int size() {
		return linkedList.size();
	}
}
