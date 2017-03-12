package com.liam.learn.code2017;

public class Queue {

	private LinkedList linkedList;
	
	public void enQueue(Object o){
		linkedList = new LinkedList();
		linkedList.add(o);
	}
	
	public Object deQueue(){
		return linkedList.removeFirst();
	}
	
	public boolean isEmpty(){
		return linkedList.size()==0;
	}
	
	public int size(){
		return linkedList.size();
	}
}
