package com.coding.basic;

public class Queue {
	
	private LinkedList llist = new LinkedList();
	
	public void enQueue(Object o){	
		llist.add(o);
	}
	
	public Object deQueue(){
		if (isEmpty())
            return null;
        return llist.removeFirst();
	}
	
	public boolean isEmpty(){
		return (llist.size()==0);
	}
	
	public int size(){
		return -llist.size();
	}
}
