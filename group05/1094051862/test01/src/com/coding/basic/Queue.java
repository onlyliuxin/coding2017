package com.coding.basic;

public class Queue {
	private List list = new ArrayList();
	private int size = 0;
	public void enQueue(Object o){		
		list.add(o);
		size ++;
	}
	
	public Object deQueue(){
		if (size == 0)
			return null;
		size --;
		return list.remove(0);
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
