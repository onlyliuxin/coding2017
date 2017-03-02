package com.coding.basic;

import java.util.ArrayList;

public class Queue<E> {
	E[] element;
	//为了测试方便暂时将长度设置为5
	private static final int DEFAULT_SIZE=5;
	int front,rear;//分别为队首和队尾下标
	
	public Queue() {
		this(DEFAULT_SIZE);
	}
	public 	Queue(int size) {
		element = (E[])(new Object[size]);
		front = 0;
		rear = 0;
	}
	
	public void enQueue(E e){	
		if(((rear+1)%element.length) == front) {
			System.out.println("队列已满无法入队");
		} else {
			element[rear] = e;
			rear=(rear+1)%element.length;			
		}		
	}
	
	public E deQueue(){
		if(rear == front) {
			return null;
		} else {
			E e = element[front];
			front = (front + 1)%element.length;
			return e;
		}
		
	}
	
	public boolean isEmpty(){
		return rear == front;
	}
	
	public int size(){
		if(rear > front){
			return rear-front;
		} else {
			return (element.length+1)-(front-rear);
		}
	}
	
}
