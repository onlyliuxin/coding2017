package com.coding.basic.week01;

public class Queue {
	private LinkedList list = new LinkedList();
	//入队列
	public void enQueue(Object o){		
		list.add(o);
	}
	//出队列
	public Object deQueue(){
		if(list.size() == 0) {
			return null;
		}
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
