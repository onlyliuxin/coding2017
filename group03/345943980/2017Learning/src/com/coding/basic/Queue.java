package com.coding.basic;

/**
 * 队列（堆）：特点，先进先出
 * 
 * @author Administrator
 * 
 */
public class Queue {

	private LinkedList linkedList = new LinkedList();
	private int size = 0;

	/**
	 * 入队列
	 * 
	 * @param o
	 */
	public void enQueue(Object obj) {
		linkedList.add(obj);
		size++;
	}

	/**
	 * 出队列
	 * 
	 * @return
	 */
	public Object deQueue() {
		Object obj = linkedList.remove(0);
		size--;
		return obj;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
