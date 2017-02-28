package com.coding.basic;

public class Queue {
	private LinkedList data;
	private int size;
	
	public Queue(){
		data = new LinkedList();
	}
	
	public void enQueue(Object o){	
		data.addLast(o);
		size++;
	}
	
	public Object deQueue(){
		size --;
		return data.removeFirst();
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
