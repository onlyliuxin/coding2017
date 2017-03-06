package com.ace.coding;

public class Queue {
	private ArrayList arrayList = new ArrayList();
	private int size = 0;
	
	public void enQueue(Object data){
		arrayList.add(size(), data);
		size++;
	}
	
	public Object deQueue(){
		if(isEmpty()){
			throw new IndexOutOfBoundsException("The Queue is Empty.");
		}
		size--;
		return arrayList.remove(0);
	}
	
	public boolean isEmpty(){
		return size()>0;
	}
	
	public int size(){
		return size;
	}
}
