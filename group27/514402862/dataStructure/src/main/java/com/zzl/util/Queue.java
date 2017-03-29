package com.zzl.util;

public class Queue {
	
	private ArrayList elementData = new ArrayList();
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.remove(0);
	}

	public boolean isEmpty(){
		int len = elementData.size();
		
		return len == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
