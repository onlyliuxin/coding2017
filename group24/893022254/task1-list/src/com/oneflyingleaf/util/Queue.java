package com.oneflyingleaf.util;

public class Queue {
	
	private LinkedList list;
	
	
	public void enQueue(Object o){		
		if(list == null){
			list = new LinkedList();
		}
		list.add(o);
	}
	
	public Object deQueue(){
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return list == null || list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
