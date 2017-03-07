package com.coding.basic;

import java.util.NoSuchElementException;

public class Queue {
	private int size;
	private LinkedList list = new LinkedList();
	
	public void enQueue(Object o){
		list.addLast(o);;
		size++;
	}
	
	public Object deQueue(){
		if(size<=0){
			throw new NoSuchElementException();
		}
		Object val = list.removeFirst();
		size--;
		return val;
	}
	
	public boolean isEmpty(){
		boolean flag = false;
		if(size >= 0){
			flag = true;
		}
		return flag;
	}
	
	public int size(){
		return size;
	}

}
