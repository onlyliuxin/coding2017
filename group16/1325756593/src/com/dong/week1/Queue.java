package com.dong.week1;

public class Queue {
	private ArrayList elementData = new ArrayList();
	
	public void enQueue(Object o){		
		elementData.add(elementData.size(), o);
	}
	
	public Object deQueue(){
		if(elementData.size()==0){
			throw new IndexOutOfBoundsException("╤сапн╙©у");
		}
		return elementData.remove(0);
	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
}
