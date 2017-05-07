package com.coding.basic.queue;

public class CircleQueue {
	private int size;
	private int  real;
	private int front;
	private Object[] object;
	public CircleQueue(int size){
		this.size=size+1;
		object=new Object[this.size];
		this.real=0;
		this.front=0;
	}
	public boolean isEmpty(){
		return real==front;
	}
	public int size(){
		if(isEmpty())
			return 0;
		else
			return real;
	}
	public Object deQueue(){
		 if(!isEmpty()){
			
			Object o=object[front];
			object[front]=null;
			front=(front+1)%size;
			return  o;
		 }
		return null;
	}
	public void enQueue(Object o){
		if(!queueFull()){
			object[real]=o;
			real=(real+1)%size;
		}
	}
	private boolean queueFull(){
		
		return (real+1)%size==front;
	}
}
