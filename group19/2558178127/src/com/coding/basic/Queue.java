package com.coding.basic;

public class Queue {
	private LinkedList list = new LinkedList();  
    private int size = 0;  
    
	public void enQueue(Object o){	
		size++;  
        list.addLast(o);  
	}
	
	public Object deQueue(){
		size--;
        return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return size>0;
	}
	
	public int size(){
		return size;
	}
}
