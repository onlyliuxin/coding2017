package com.tiaozaoj;

public class NewQueue {
	NewLinkedList linkedList = new NewLinkedList();
	private int size = 0;
	
	public void enQueue(Object o){
		linkedList.add(o);
	}
	
	public Object deQueue(Object o){
		Object toDel = linkedList.get(0);
		linkedList.remove(0);
		return toDel;
	}
	
	public boolean isEmpty(){
		return linkedList.size()>0?false:true;
	}
	
	public int size(){
		return linkedList.size();
	}
}
