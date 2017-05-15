package com.coding.basic.stack.myselfQueue;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	//用数组来保存循环队列的元素
	private Object[] elementData;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	private int size = 0;
	
	public CircleQueue(int capcility){
		elementData = new Object[capcility];
	}
	
	public boolean isEmpty() {
		
		return front == rear;
        
    }
	
	public boolean isFull(){
		return  size == elementData.length;
	}

    public int size() {
    	
        return size;
    }

    

    public void enQueue(E data) {
        if (isFull()){
        	throw new RuntimeException("the queue is full");
        }
        
        elementData[rear++] = data;
        size++;
    }

    public E deQueue() {
        if (isEmpty()){
        	throw new RuntimeException("the queue is empty");
        }
        E data = (E) elementData[front];
        elementData[front] = null;
        front = (front + 1) % elementData.length;
        size --;
        return data;
        
    }
}
