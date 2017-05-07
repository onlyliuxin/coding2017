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
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;//指向下一个空位
	//实际大小
	private int size = 0;
	
	public CircleQueue(int i) {
		this.elementData = new Object[i];
	}
	
	public boolean isEmpty() {
		return size == 0;
    }
	
	public boolean isFull() {	
		return size == elementData.length;
	}
	
    public int size() {
        return size;
    }
    
    public void enQueue(E data) {
        if(isFull()){
        	throw new RuntimeException("The queue is full!!!");
        }
        
        elementData[rear] = data;
        rear = (rear + 1) % elementData.length;
        size++;
    }
    
    public E deQueue() {
    	if(isEmpty()){
    		throw new RuntimeException("The queue is empty!!!");
    	}
    	
    	E result = (E) elementData[front];
    	elementData[front] = null;
    	front = (front + 1) % elementData.length;
    	size--;
        return result;
    }
    
}
