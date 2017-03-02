package com.coding.basic;

public class Queue {
	private LinkedList elementData = new LinkedList();
	
	public void enQueue(Object o){
		this.elementData.addLast(o);
	}
	
	public Object deQueue(){
		return this.elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		if(this.elementData.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		return this.elementData.size();
	}
	
	public String toString(){
		return this.elementData.toString();
	}
}
