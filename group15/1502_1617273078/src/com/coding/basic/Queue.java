package com.coding.basic;

public class Queue {
	LinkedList linst = new LinkedList();
	public void enQueue(Object o){
		linst.addLast(o);
	}
	
	public Object deQueue(){

		return linst.removeFirst();
	}
	
	public boolean isEmpty(){
		if (linst.size() == 0) {
			return true;
		}else {
			return false;
		}
		//return ;
	}
	
	public int size(){
		return linst.size();
	}
}
