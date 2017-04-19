package com.coding.basic;

import com.coding.basic.linklist.LinkedList;

public class Queue {
	private LinkedList list = new LinkedList();
	
	public void enQueue(Object o){	
		list.add(o);
	}
	
	public Object deQueue(){
		if(list.size()==0)throw new RuntimeException("Queue is empty.");
		return list.removeFirst();
	}
	
	public boolean isEmpty(){
		return (list.size()==0);
	}
	
	public int size(){
		return list.size();
	}
	
	@Override
	public String toString(){
		return this.list.toString();
	}
	
	public static void main(String[] args){
		Queue q = new Queue();
		q.enQueue("aaa");
		q.enQueue("bbb");
		System.out.println(q);
		System.out.println(q.isEmpty());
		System.out.println(q.size());
		q.deQueue();
		q.deQueue();
		System.out.println(q);
		System.out.println(q.isEmpty());
		System.out.println(q.size());
		//q.deQueue();
	}
}
