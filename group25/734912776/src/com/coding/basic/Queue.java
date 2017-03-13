package com.coding.basic;

import java.util.Arrays;

public class Queue {

	private Object[] queue;
	private transient int size;
	private transient int front;
	private transient int rear;
	
	public Queue(){
		this.queue=new Object[10];
	}
	
	public Queue(int capacity){
		this.queue=new Object[capacity];
	}
	
	private void ensureCapacity(int minCapacity){
		if(minCapacity>queue.length){
			grow(minCapacity);
		}
	}
	
	private void grow(int minCapacity) {
		int oldCapacity=queue.length;
		int newCapacity=oldCapacity+(oldCapacity>>1);
		if(newCapacity<minCapacity){
			newCapacity=minCapacity;
		}
		if(newCapacity>Integer.MAX_VALUE){
			newCapacity=Integer.MAX_VALUE;
		}
		queue=Arrays.copyOf(queue, newCapacity);
		
	}
	
	public void enQueue(Object o){
		ensureCapacity(size+1);
		 if(rear+1==front){
			 throw new IllegalStateException();
	        }else{  
	            queue[rear]=o;  
	            rear=rear+1; 
	            size++;
	        }
	}
	
	public Object deQueue(){
		if(rear==front)  
			throw new IllegalStateException(); 
        else{  
        	Object obj =queue[front];  
            front=front+1; 
            size--;
            return obj;  
        } 
	}
	
	public boolean isEmpty(){
		if(rear==front){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		return size;
	}
}
