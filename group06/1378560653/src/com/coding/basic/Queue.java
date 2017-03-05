package com.coding.basic;

public class Queue {
	private LinkedList linkedlist = new LinkedList();
	
	public void enQueue(Object o){
		linkedlist.add(o);
	}
	
	public Object deQueue(){
		if(!isEmpty()){
			return linkedlist.get(0);
		}else{
			return null;
		}
	}
	
	public boolean isEmpty(){
		if(size() > 0){
			return false;
		}else{
			return true;
		}
	}
	
	public int size(){
		return linkedlist.size();
	}
}
