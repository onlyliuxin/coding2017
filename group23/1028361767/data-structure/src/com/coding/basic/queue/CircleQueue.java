package com.coding.basic.queue;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	private int maxSize = DEFAULT_SIZE;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public CircleQueue(int maxSize){
		elementData = new Object[maxSize];
		this.maxSize = maxSize;
	}
	
	public boolean isEmpty() {
		return rear == front;
        
    }

    public int size() {
        return rear - front;
    }

    

    public void enQueue(E data) {
        if((rear+1)%maxSize == front){
        	throw new FullQueueException();
        }
        elementData[rear] = data;
        rear = (rear + 1) % maxSize;
    }

    public E deQueue() {
    	if(rear == front){
    		throw new EmptyQueueException();
    	}
    	E data = (E)elementData[front];
    	elementData[front] = null;
    	front = (front + 1) % maxSize;
        return data;
    }
    
}
