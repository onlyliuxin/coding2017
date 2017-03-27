package com.github.mrwengq.first;

public class Queue {

	private ArrayList elementData;
	public Queue() {
		elementData = new ArrayList();
	}

	public void enQueue(Object o) {
		elementData.add(o);
	}

	public Object deQueue() {
		Object ob = null;
		ob = elementData.get(0);
		elementData.remove(0);
		return ob;
	}

	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	public int size() {
		return elementData.size();
	}

}
