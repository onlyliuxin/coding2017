package com.coding.basic;

/*
 * 先入后出 
 * 链表实现
 * 链表头是栈顶，链表尾是栈底
 */
public class Stack {
	
	private LinkedList linkedList;
	
	public Stack()
	{
		this.linkedList = new LinkedList();
	}
	
	public void push(Object o){	
		this.linkedList.addFirst(o);
	}
	
	public Object pop(){
		return this.linkedList.removeFirst();
	}
	
	public Object peek(){
		return this.linkedList.get(0);
	}
	public boolean isEmpty(){
		return this.size() == 0;
	}
	public int size(){
		return this.linkedList.size();
	}
}
