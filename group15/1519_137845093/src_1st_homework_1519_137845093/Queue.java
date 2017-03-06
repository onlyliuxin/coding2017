package com.coding.basic;
import java.util.NoSuchElementException;

public class Queue {
	private int size;
	private LinkedList list = new LinkedList();	
	
	public void enQueue(Object o){
		list.addLast(o);
		size++;
	}
	
	public Object deQueue(){
		if(size<=0){
			throw new NoSuchElementException();
		}
		Object deQueue = list.removeLast();
		size--;
		return deQueue;
	}
	
	public boolean isEmpty(){
		return (size>=0);
	}
	
	public int size(){
		return size;
	}
}
