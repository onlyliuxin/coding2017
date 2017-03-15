package com.coding.basic;

/**
 * 队列数据结构
 * Queue
 * @author greenhills
 * 2017年2月25日 下午9:50:04
 */
public class Queue {
	private LinkedList elementData = new LinkedList();
	
	/**
	 * 入队
	 * @param o
	 */
	public void enQueue(Object o){
		elementData.addLast(o);
	}
	
	/**
	 * 出队
	 * @return
	 */
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	/**
	 * 判断是否为空
	 * @return
	 */
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	/**
	 * 获取栈内数据量
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}
