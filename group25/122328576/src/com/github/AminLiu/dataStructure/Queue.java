package com.github.AminLiu.dataStructure;

import java.util.EmptyStackException;

public class Queue {

	private LinkedList linkedList = new LinkedList();

	public void enQueue(Object o) {
		linkedList.add(o);
	}

	public Object deQueue() {
		if (linkedList.size() == 0) {
			throw new EmptyStackException();
		}
		return linkedList.removeFirst();
	}

	public boolean isEmpty() {
		return linkedList.size() == 0;
	}

	public int size() {
		return linkedList.size();
	}

	public String toString() {
		return linkedList.toString();
	}
}
