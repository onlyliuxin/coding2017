package com.github.eloiseSJTU.coding2017.basic;

public class Queue {
	
	private int size = 0;
	
	private LinkedList elementData = new LinkedList();

	public void enQueue(Object o) {
		elementData.addLast(o);
		size++;
	}

	public Object deQueue() {
		Object o = elementData.get(0);
		elementData.removeFirst();
		size--;
		return o;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
