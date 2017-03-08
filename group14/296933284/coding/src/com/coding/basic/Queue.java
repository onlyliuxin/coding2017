package com.coding.basic;

/**
 * Queue 实现
 * First In First Out
 * 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class Queue {
	
	private LinkedList elementData = new LinkedList();
	
	/**
	 * 向队列中插入元素
	 * 
	 * @param obj
	 */
	public void enQueue(Object obj){		
		elementData.addLast(obj);
	}
	
	/**
	 * 删除队首元素
	 * 
	 * @return
	 */
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	/**
	 * 判断队列是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	/**
	 * 返回队列的元素个数
	 * 
	 * @return
	 */
	public int size(){
		return elementData.size();
	}
}
