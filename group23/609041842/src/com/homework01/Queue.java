package com.homework01;

public class Queue {

	private LinkedList lk = new LinkedList();
	public void enQueue(Object o){
		lk.add(o);
	}
	public void deQueue(){
		lk.remove(lk.size-1);
	}
	public boolean isEmpty(){
		if(lk.size == 0)
			return true;
		return false;
	}
}
