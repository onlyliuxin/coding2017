package com.github.wdn.coding2017.basic;

/**
 * 队列使用链表实现比较简单理论上是无界队列
 * 如果使用数组需要处理很多问题比如长度限制，元素的复制
 */
public class Queue {
	private LinkedList queue = new LinkedList();
	public void enQueue(Object o){
		queue.add(o);
	}
	
	public Object deQueue(){
		return queue.remove(0);
	}
	
	public boolean isEmpty(){
		return queue.size()==0;
	}
	
	public int size(){
		return queue.size();
	}
}
