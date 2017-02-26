package com.easy.util.myqueue;

import com.easy.util.mylinkedlist.LinkedList;

public class Queue {
	
	LinkedList linkedList;
	public Queue(){
		linkedList=new LinkedList();
	}
	
	public void enQueue(Object o){	
		linkedList.add(o);
	}
	
	public Object deQueue(){
		return linkedList.removeFirst();
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return linkedList.size();
	}
}
