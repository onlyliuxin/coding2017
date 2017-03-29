//package com.coding.basic;

public class Queue {
	private LinkedList l=new LinkedList();
	public Queue (){}
	public void enQueue(Object o){	
		l.addLast(o);
	}
	
	public Object deQueue(){
		Object o=l.removeFirst();
		return o;
	}
	
	public boolean isEmpty(){
		boolean flag=true;
		if(l.size>0)
		{
			flag=false;
		}
		return flag;
	}
	
	public int size(){
		return l.size;
	}
}

