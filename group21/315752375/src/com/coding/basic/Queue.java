package com.coding.basic;

public class Queue<T extends Comparable<T>> {
	LinkedList linkedList=new LinkedList();
	public void enQueue(T o){
		linkedList.addLast(o);
	}
	
	public T deQueue(){
		return (T)linkedList.removeFirst();
	}
	public boolean isEmpty(){
		return linkedList.size()==0;
	}
	
	public int size(){
		return linkedList.size();
	}
}
