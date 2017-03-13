package com.coding.week1;

public class Queue {
	ArrayList list = new ArrayList();
	public void enQueue(Object o){	
		list.add(o);
	}
	
	public Object deQueue(){
		return list.remove(0);
	}
	
	public boolean isEmpty(){
		if(list.size()==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return list.size();
	}
}
