package com.coding.basic;

public class Queue {

	LinkedList l = new LinkedList();
	public void enQueue(Object o){
		l.addLast(o);
	}
	
	public Object deQueue(){
		return l.removeFirst();
	}
	
	public boolean isEmpty(){
		if (l.size() == 0)
		  return true;
		else
		  return false;	
	}
	
	public int size(){
		return l.size();
	}

}
