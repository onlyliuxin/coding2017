package com.github.ipk2015.coding2017.basic.queue;



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
	private int rear = 0;
	private int size = 0;
	public boolean isEmpty() {
		return size == 0;
        
    }

    public int size() {
        return size;
    }

    

    public void enQueue(E data) {
        if(size == DEFAULT_SIZE){
        	throw new RuntimeException("queue size is max");
        }
        elementData[rear] = data;
        rear = (rear+1)%DEFAULT_SIZE;
        size++;
    }

    public E deQueue() {
    	if(size == 0){
        	throw new RuntimeException("queue size is 0");
        }
    	E e = (E)elementData[front];
    	elementData[front] = null;
    	front = (front+1)%DEFAULT_SIZE;
    	size--;
        return e;
    }
}
