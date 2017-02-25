package com.coding.basic;

public class Queue {
	
	private LinkedList list = new LinkedList();
	
	public void enQueue(Object o){	
		list.addLast(o);
	}
	
	public Object deQueue(){
		Object ret = list.removeFirst();
		return ret;
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
