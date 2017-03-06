package com.coding.basic;

public class Queue {
	
	private LinkedList elementData = new LinkedList();
	//1.进队列
	public void enQueue(Object o){		
		elementData.addFirst(o);
	}
	//2.出队列
	public Object deQueue(){
		return elementData.removeLast();
	}
	
	
    //3.是否为空	
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	//4.大小
	public int size(){
		return elementData.size();
	}
}
