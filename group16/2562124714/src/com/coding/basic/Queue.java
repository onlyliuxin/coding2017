package com.coding.basic;

public class Queue {
	private ArrayList elementData = new ArrayList();

	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){


		return elementData.get(1);
	}
	
	public boolean isEmpty(){

		return elementData.size() == 0 ? true : false;
	}
	
	public int size(){
		return elementData.size();
	}
}
