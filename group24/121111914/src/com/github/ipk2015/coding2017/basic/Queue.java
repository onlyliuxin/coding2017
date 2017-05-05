package com.github.ipk2015.coding2017.basic;

import com.github.ipk2015.coding2017.basic.linkedlist.LinkedList;

public class Queue {
	private LinkedList elementDatas=new LinkedList();
	public void enQueue(Object o){		
		elementDatas.add(o);
	}
	public Object deQueue(){
		return elementDatas.removeFirst();
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return elementDatas.size();
	}
}
