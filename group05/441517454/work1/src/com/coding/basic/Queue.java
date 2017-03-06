package com.coding.basic;

public class Queue {
	private ArrayList elementData = new ArrayList();
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	public Object deQueue(){
		
		if(elementData.size()>0)
			
		{ 
		elementData.remove(0);
		return elementData.remove(0);
		}
		else return null;
		}
	
	public boolean isEmpty(){
		if(elementData.size()>0)
			return false;
			else return true;
		
	}
	
	public int size(){
		return elementData.size();
	}
}
