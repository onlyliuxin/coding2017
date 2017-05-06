
package com.coding.basic;

import com.coding.basic.linklist.LinkedList;

/**
 * author by zhougd 20170306
 * 链表实现队列
 */
public class Queue {
	private java.util.Queue a;;
	/**
	 * 队列体，初始100个元素
	 */
	private List queue = new LinkedList();

	public Queue(){}

	/**
	 * 入队
	 * @param o
	 */
	public void enQueue(Object o){
		queue.add(o);
	}

	/**
	 * 出队
	 * @return
	 */
	public Object deQueue(){
		return queue.remove(0);
	}

	/**
	 * 队列是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return queue == null || queue.size() <= 0;
	}

	/**
	 * 获取队列大小
	 * @return
	 */
	public int size(){
		return queue.size();
	}
}
