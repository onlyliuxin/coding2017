package com.pan.queue;

public class CircleQueue <E> {

	//用数组来保存循环队列的元素
	private Object[] elementData ;
	int size = 0;
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public CircleQueue(int capacity){
		elementData = new Object[capacity];
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
        if(isFull()){
        	throw new RuntimeException("The queue is full");
        }
        elementData[rear++] = data;
        size++;
    }

    public E deQueue() {
        if(isEmpty()){
        	throw new RuntimeException("The queue is empty");
        }
        E data = (E)elementData[front];
        elementData[front] = null;
        front = (front+1) % elementData.length;
        size --;
        return data;
    }
}
