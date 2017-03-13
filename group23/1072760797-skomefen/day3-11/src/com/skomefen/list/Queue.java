package com.skomefen.list;

public class Queue {
	ArrayList elementData = new ArrayList(); 
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	public Object deQueue(){
		
		if(elementData.size()!=0)
			return elementData.remove(0);
		return null;
		
	}
	
	public boolean isEmpty(){
		if(elementData.size()==0)
			return true;
		return false;
	}
	
	public int size(){
		return elementData.size();
	}
}
