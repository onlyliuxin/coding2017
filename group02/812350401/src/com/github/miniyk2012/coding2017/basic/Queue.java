package com.github.miniyk2012.coding2017.basic;

public class Queue {
	
	// 队列入口 -》1，2，3，4 -》队列出口
	private LinkedList aList = new LinkedList();
	
	public void enQueue(Object o){	
		aList.addFirst(o);
	}
	
	public Object deQueue(){
		return aList.removeLast();
	}
	
	public boolean isEmpty(){
		return aList.size() == 0;
	}
	
	public int size(){
		return aList.size();
	}
}
