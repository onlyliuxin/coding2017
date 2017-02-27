package com.coding.mybasic;

public class Queue {
	private LinkedList linkedList = new LinkedList();
	public void enQueue(Object o){		
		linkedList.add(o);
	}
	
	public Object deQueue(){
		checkEmptyQueue();
		return linkedList.remove(0);
	}
	
	public boolean isEmpty(){
		return size() <= 0;
	}
	
	public int size(){
		return linkedList.size();
	}
	
	/**
	 * 检查队列是否为空
	 */
	private void checkEmptyQueue() {
		if(isEmpty()){
			throw new RuntimeException("size:" + size() + " 空队列");
		}
	}
}
