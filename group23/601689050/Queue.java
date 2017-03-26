package com.bjsxd.test;

public class Queue{
	private Object[] Q;
	int front;
	int rear;
	public Queue(){
		Q = new Object[100];
	}
	public Queue(int size){
		Q = new Object[size];
		front = 0;
		rear = 0;
	}
	public void enQueue(Object o){
		if((rear+1)%Q.length == front){
			System.out.println("Õ»Âú£¡");
		}else{
			Q[rear] = o;
			rear = rear+1;
		}
	}
	
	public Object deQueue(){
		if (rear == front)
		return null;
		else{
			Object temp = Q[front];
			front = front+1;
			return temp;
		}
	}
	
	public boolean isEmpty(){
		return rear == front;
	}
	
	public int size(){
		if(rear > front){
			return rear - front;
		}else
			return Q.length-1;
	}

}
