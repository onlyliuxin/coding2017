package com.ikook.basic_data_structure;

/**
 * @author ikook;  QQ号码: 935542673
 */
public class MyQueue {
	
	private MyLinkedList queue = new MyLinkedList();
	
	/**
	 * 入队操作
	 * @param obj
	 */
	public void enQueue(Object obj) {
		queue.addLast(obj);
	}
	
	/**
	 * 出队操作
	 * @return
	 */
	public Object deQueue() {
		return queue.removeFirst();
	}
	
	/**
	 * 队列的长度
	 * @return
	 */
	public int size() {
		return queue.size();
	}
	
	/**
	 * 队列是否为空
	 * @return
	 */
	public boolean isEmpty() {
		return queue.isEmpty();
	}

}
