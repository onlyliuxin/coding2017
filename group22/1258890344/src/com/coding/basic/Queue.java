package com.coding.basic;

public class Queue {
	private LinkedList list=new LinkedList();
	private int size=0;
	public void enQueue(Object o){	
		list.addLast(o);
		size++;
	}
	
	public Object deQueue(){
		Object o= list.removeFirst();
		size--;
		return o;
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return size;
	}
}
