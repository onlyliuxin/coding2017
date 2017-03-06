package com.coding.basic;

public class Queue {
	private static final int CAPACITY = 10;
	private  int size;
	private  int front;
	private  int tail;
	private  Object[] array;
	
	public Queue(){
		this.size = CAPACITY;
		array = new Object[size];
		front = tail = 0;
	}
	
	
	public void enQueue(Object o) throws Exception{	
		if (size() == size -1)
			throw new Exception("Queue is full");
		array[tail] = o;
		tail = (tail +1) % size;
	}
	
	public Object deQueue() throws Exception{
		Object o;
        if (isEmpty())
            throw new Exception("Queue is empty");
        o = array[front];
        front = (front + 1) % size;
        return o;
	}
	
	public boolean isEmpty(){
		return (front==tail);
	}
	
	public int size(){
		if (isEmpty())
			return 0;
		else
		return (size + tail - front) % size;
	}
	
}
