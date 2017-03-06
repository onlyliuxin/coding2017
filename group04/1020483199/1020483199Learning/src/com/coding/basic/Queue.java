package com.coding.basic;

import java.util.Arrays;

public class Queue {
	/**
	 * 队列长度
	 */
	private int size;
	
	private LinkedList elementData = new LinkedList();
	/**
	 * 入队操作[向栈尾插入]
	 * @param o
	 */
	public void enQueue(Object o){
		elementData.add(o);
		size++;
	}
	
	public Object deQueue(){
		Object obj = elementData.removeFirst();
		size--;
		return obj;
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}else{
			return false;
		}
		
	}
	
	public int size(){
		return size;
	}
	
}
