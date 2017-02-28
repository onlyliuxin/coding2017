package com.coding.basic;

public class Queue extends LinkedList{

	//队列尾部加一个
	public void enQueue(Object o){
		super.addLast(o);
	}
	//队列头部减少一个
	public Object deQueue(){
		return super.removeFirst();
	}
	
	public boolean isEmpty(){
		return super.size()==0;
	}
	
	public int size(){
		return super.size();
	}
}
