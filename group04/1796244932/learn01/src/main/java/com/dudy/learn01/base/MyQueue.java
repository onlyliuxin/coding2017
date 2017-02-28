package com.dudy.learn01.base;
public class MyQueue {

	private  MyLinkedList  elementData = new MyLinkedList();

	public void enQueue(Object o){
		elementData.addLast(o);
	}
	
	public Object deQueue(){

		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0 ;
	}
	
	public int size(){
		return elementData.size();
	}
}