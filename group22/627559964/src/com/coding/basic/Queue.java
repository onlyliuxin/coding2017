package com.coding.basic;

/**
 * 自定义队列
 * 
 * @author xiongrui233
 *
 */
public class Queue {

	private LinkedList elements = new LinkedList();;
	
	private int size = 0;

	public void enQueue(Object o) {
		elements.add(o);
		size ++;
	}

	public Object deQueue() {
		size --;
		return elements.removeLast();
	}

	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	public int size() {
		return size;
	}
}