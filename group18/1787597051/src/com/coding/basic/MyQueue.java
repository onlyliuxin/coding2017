package com.coding.basic;

public class MyQueue {
	private int size;
	MyLinkedList mll = new MyLinkedList();
	public MyQueue() {
	}
	public void enQueue(Object o){	
		mll.add(o);
		size++;
	}
	
	public Object deQueue(){
		size--;
		return mll.removeFirst();
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
