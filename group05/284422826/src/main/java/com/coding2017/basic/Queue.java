package com.coding2017.basic;

import com.coding2017.basic.linklist.LinkedList;

import java.util.EmptyStackException;

public class Queue {
	private LinkedList elementData = new LinkedList();
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		if (elementData.size() == 0) {
			throw new EmptyStackException();
		}
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
