package com.coding.basic;

public class Queue {
	private LinkedList list;
	public Queue(){
		list = new LinkedList();
	}
	/* 向队列中插入数据 */
	public void enQueue(Object o){
		list.add(o);
	}
	 
	/* 从队列中删除数据 */
	public Object deQueue() {
		return list.remove(0);
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
