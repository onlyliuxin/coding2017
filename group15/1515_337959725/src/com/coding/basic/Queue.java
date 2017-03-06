package com.coding.basic;

public class Queue {
	private LinkedListTest list = new LinkedListTest();
	public void enQueue(Object o){
		list.add(o);
	}
	
	public Object deQueue(){
		Object obj = list.removeFirst();
		return obj;
	}
	
	public boolean isEmpty(){
		int size = list.size();
		if(size==0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		int size = list.size();
		return size;
	}
}
