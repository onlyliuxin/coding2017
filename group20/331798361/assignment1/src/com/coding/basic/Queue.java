package com.coding.basic;


public class Queue {
	private LinkedList list = new LinkedList();

	public void enQueue(Object o){
		list.add(o);
	}
	
	public Object deQueue(){
		int length = list.size();
		if (length == 0) {
			return null;
		}
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		if (list.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public int size(){
		return list.size();
	}
}
