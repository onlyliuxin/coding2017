package com.coding.basic;

import java.util.NoSuchElementException;

public class Queue {
	Object[] elementData;
	int maxsize;
	int size =0;
	int head=0;
	int last=-1;
	public Queue(int i){
		maxsize = i;
		elementData = new Object[maxsize];		
	}
	
	public void enQueue(Object o){	
		
		if(size == maxsize){
			throw new IllegalStateException("Queue full");
		}		
			elementData[size++]=o;		
			last=size;
		
	}
	
	public Object deQueue(){
	
		return null;
	}
	
	public boolean isEmpty(){
		if(this.size==0){
			return true;
		}
			
		return false;
	}
	
	public int size(){
		return this.size;
	}
}
