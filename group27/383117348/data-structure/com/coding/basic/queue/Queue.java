package com.coding.basic.queue;

import org.junit.Test;

import com.coding.basic.linklist.LinkedList;

public class Queue {
	private int size = 0;
	private LinkedList linkedList = new LinkedList();

	/**
	 * 入队方法
	 * 
	 * @param o
	 */
	public void enQueue(Object o) {
		linkedList.add(o);
		size++;
	}

	/**
	 * 出队方法
	 * 
	 * @return
	 */
	public Object deQueue() {
		Object result = linkedList.removeFirst();
		size--;
		return result;
	}

	/**
	 * 判断队列是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 获取队列的长度
	 * 
	 * @return
	 */
	public int size() {
		return size;
	}

	/*------------------------------------------------------单元测试----------------------------------------------------*/
	/**
	 * 入队测试
	 */
	@Test
	public void enQueueTest() {
		Queue queue = new Queue();
		queue.enQueue(1);
	}

	/**
	 * 出队测试
	 */
	@Test
	public void deQueueTest() {
		Queue queue = new Queue();
		for (int x = 0; x < 100; x++) {
			queue.enQueue(x);
		}
		for (int x = 0; x < queue.size();) {
			System.out.println(queue.deQueue());
		}
	}

	public static void main(String[] args) {
	}
}
