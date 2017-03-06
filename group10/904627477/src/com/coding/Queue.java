package com.coding;

public class Queue {
	
	private ArrayList elementData = new ArrayList();
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		if(elementData.size()==0){
			return null;
		}
		return elementData.remove(0);
	}
	
	public boolean isEmpty(){
		if(elementData.size()==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
