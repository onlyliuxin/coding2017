package com.coding.basic;

/*
 * 先入先出
 * 链表实现
 * 链表头是队列头
 * 链表尾是队列尾
 */
public class Queue {
	
	private LinkedList linkedList;
	
	public Queue()
	{
		this.linkedList = new LinkedList();
	}
	
	//往队列头添加新对象
	public void enQueue(Object o){	
		if(o == null)
			return;
		
		this.linkedList.addFirst(o);
	}
	
	//从队列尾移除对象
	public Object deQueue(){
		return this.linkedList.removeLast();
	}
	
	public boolean isEmpty(){
		return this.size() == 0;
	}
	
	public int size(){
		return this.linkedList.size();
	}
}
