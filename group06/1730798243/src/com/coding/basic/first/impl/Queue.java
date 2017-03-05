package com.coding.basic.first.impl;

/**
 * 基本数据结构-队列
 * @author zap
 *
 */

public class Queue {
	
	private LinkedList linkedList = new LinkedList();
	
	/**
	 * 入队操作
	 * @param o 入队的元素
	 */
	public void enQueue(Object o){		
		linkedList.addLast(o);
	}
	
	/**
	 * 出队操作
	 * @return 返回出队的元素
	 */
	public Object deQueue(){
			if(!isEmpty())
				return linkedList.removeFirst();
			return null;
	}
	
	/**
	 * 判断队列是否为空
	 * @return 为空返回true，否则返回false
	 */
	public boolean isEmpty(){
		return linkedList.size() == 0 ? true : false;
	}
	
	/**
	 * 返回队列的长度
	 * @return
	 */
	public int size(){
		return linkedList.size();
	}
}
