package com.coding.basic;

public class Queue {
	private LinkedList ll = null;
	
	public Queue(){
		ll = new LinkedList();
	}
	
	public void enQueue(Object o){	
		ll.add(o);
	}
	
	public Object deQueue() throws IndexOutOfBoundsException{
		try{
			return ll.remove(0);
		}catch(IndexOutOfBoundsException ie){
			throw new IndexOutOfBoundsException(ie.getMessage());
		}
		 
	}
	
	public boolean isEmpty(){
		return (ll.size() == 0) ? true : false;
	}
	
	public int size(){
		return ll.size();
	}
}
