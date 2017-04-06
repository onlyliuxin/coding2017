package com.github.qq809203042.coding2017.basic.structures;

import java.util.LinkedList;

/*
 * 实现队列的结构，使用链表结构
 */
public class MyQueue {
	private LinkedList<Object> queueList = new LinkedList<>();
	private int size;
//	进入队列
	public void enQueue(Object obj){		
		queueList.addLast(obj);
		size++;	
	}
	
	public Object deQueue(){
		Object removeElement = queueList.removeFirst();
		size--;
		return removeElement;
	}
	
	public boolean isEmpty(){
		return size == 0;
	}
	
	public int size(){
		return size;
	}
}
