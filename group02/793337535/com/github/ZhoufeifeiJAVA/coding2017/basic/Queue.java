package com.github.ZhoufeifeiJAVA.coding2017.basic;

public class Queue {
	private MyLinkedList llist = null;
	Queue(){
		llist = new MyLinkedList();
	}
	public void enQueue(Object o){	
		llist.add(o);
	}
	
	public Object deQueue(){
		return llist.removeFirst();
	}
	
	public boolean isEmpty(){
		if(llist.size() != 0)
			return true;
		else
			return false;
	}
	
	public int size(){
		return llist.size();
	}
}
