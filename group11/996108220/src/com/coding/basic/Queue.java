package com.coding.basic;

public class Queue {
	//用链表实现队列
	private LinkedList elementData = new LinkedList();
	public void enQueue(Object o){
		elementData.addLast(o);;
	}
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size()==0?true:false;
	}
	
	public int size(){
		return elementData.size();
	}
}
