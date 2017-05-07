package datastructure.basic;

import datastructure.exception.EmptyQueueException;

public class Queue {
	private LinkedList list = new LinkedList();

	public void enQueue(Object o) {
		list.addLast(o);
	}

	public Object deQueue() {
	    if (isEmpty()) {
	        throw new EmptyQueueException();
        }
		return list.removeFirst();
	}

    public boolean isEmpty() {
		return list.isEmpty();
	}

	public int size() {
		return list.size();
	}
}
