package com.coding.basic.queue;

/**
 * 用数组实现循环队列
 * 没有牺牲一个元素空间，所以判断队空，队满需要取数据验证。
 * @author zhaohongxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		
		return elementData[front]==null;
        
    }

    public int size() {
    	if (isFull()) {
    		return DEFAULT_SIZE;
    	} else if (isEmpty()) {
    		return 0;
    	} else if (front < rear) {
    		return rear - front;
    	} else {
    		return rear + DEFAULT_SIZE -front; 
    	}
    }

    public void enQueue(E data) {
    	if (isFull()) {
    		throw new RuntimeException("queue is full");
    	}
        elementData[rear] = data;
        if (rear == DEFAULT_SIZE ) {
        	rear = 0;
        } else {
        	rear++;
        }
    }

    public E deQueue() {
    	if (isEmpty()) {
    		throw new RuntimeException("queue is empty");
    	}
    	E a = (E) elementData[front];
    	elementData[front] = null;
    	if (front == DEFAULT_SIZE ) {
    		front = 0;
        } else {
        	front++;
        }
        return a;
    }
    
    public boolean isFull() {
    	return !isEmpty() && front==rear;
    }
}
