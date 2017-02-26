package com.list;
//队列
public class Queue {

	Object [] element;
	
	private static int DEFAULT_SIZE = 10;
	
	int front;//头指针
	
	int rear;//尾指针
	
	public Queue(){
		this(DEFAULT_SIZE);
	}
	public Queue(int size){
		element = new Object[size];
		this.front = 0;
		this.rear = 0;
	}
	
	public boolean enQueue(Object object){
		
		if ((rear + 1) % element.length == front){
			return false;
		}else{
			element[rear] = object;
			rear = (rear + 1) % element.length;
			return true;
		}
	}
	
	public Object deQueue(){
		if (front == rear){
			return null;
		}else{
			Object object = element[front];
			front = (front + 1) % element.length;
			return object;
		}
		
	}
	
	public int size(){
		return (rear -front) & (element.length - 1);
	}
	
	public boolean isEmpty(){
		return rear == front;
	}
}
