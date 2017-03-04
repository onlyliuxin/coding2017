package com.coding.basic;
import java.util.Arrays;

public class Queue {
	private static final int CAPACITY = 10;
	private static int capacity;
	private static int front;
	private static int tail;
	private static Object[] array;
	
	public Queue(){
		this.capacity = CAPACITY;
		array = new Object[capacity];
		front = tail = 0;
	}
	
	public void enQueue(Object o) throws ExceptionQueueFull {
		if (size() == capacity -1)
			throw new ExceptionQueueFull("Queue is full");
		array[tail] = o;
		tail = (tail +1) % capacity;
	}
	
	public Object deQueue() throws ExceptionQueueEmpty{
		Object o;
        if (isEmpty())
            throw new ExceptionQueueEmpty("Queue is empty");
        o = array[front];
        front = (front + 1) % capacity;
        return o;
	}
	
	public boolean isEmpty(){
		return (front == tail);
	}
	
	public int size(){
		if (isEmpty())
			return 0;
		else
		return (capacity + tail - front) % capacity;
	}
	
	public class ExceptionQueueEmpty extends Exception {
	    // Constructor
	    public ExceptionQueueEmpty() {

	    }

	    // Constructor with parameters
	    public ExceptionQueueEmpty(String mag) {
	        System.out.println(mag);
	    }
	}
	
	public class ExceptionQueueFull extends Exception {

	    // Constructor
	    public ExceptionQueueFull() {

	    }

	    // Constructor with parameters
	    public ExceptionQueueFull(String mag) {
	        System.out.println(mag);
	    }
	}
}