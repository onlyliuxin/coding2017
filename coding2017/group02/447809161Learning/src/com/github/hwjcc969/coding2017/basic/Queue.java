package com.github.hwjcc969.coding2017.basic;

public class Queue {
	private LinkedList list = new LinkedList(); 
	public void enQueue(Object o){
		list.addLast(o);
	}
	
	public Object deQueue(){
		if (!list.isEmpty()){
			return list.removeFirst();
		}
		return "空队列";

	}
	
	public boolean isEmpty(){
		if (list.size() == 0){
			return false;
		}
		return true;
	}
	
	public int size(){
		return list.size();
	}
}
