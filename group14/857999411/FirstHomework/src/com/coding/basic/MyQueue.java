package com.coding.basic;

public class MyQueue {
	
	MyLinkedList link =new MyLinkedList();
	
	//入队
	public void enQueue(Object o){
		link.addLast(o);
	}
	//出队
	public Object deQueue(){
		return link.removeFirst();
	}
	//判断是否为空
	public boolean isEmpty(){
		return link.size == 0;
	}
	//获取长度
	public int size(){
		return link.size;
	}
}
