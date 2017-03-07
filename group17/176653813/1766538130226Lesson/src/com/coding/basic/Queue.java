package com.coding.basic;

public class Queue {
	
	private LinkList elementData = new LinkList();
	private int front = 0;
	private int rear = 0;
	
	
	public void enQueue(Object o){
		elementData.add(o);
		rear++;
	}
	public Object deQueue(){
		if(!isEmpty()){
			Object obj = elementData.remove(front);
			front++;
			return obj;
		}else{
			System.out.println("Queue is empty");
			return null;
		}
	}
	public boolean isEmpty(){
		if(front > rear){
			return true;
		}
		return false;
	}
	
	public int size(){
		return rear-front+1;
	}
}
	
