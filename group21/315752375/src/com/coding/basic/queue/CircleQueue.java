package com.coding.basic.queue;

/**
 * 用数组实现循环队列
 * @author gdw
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
		return front==rear;
        
    }
	public boolean isFull(){
		return (rear+1)%elementData.length==front;
	}
    public int size() {
    	if(rear>=front)return rear-front;
    	else return rear+elementData.length-front;
    }

    

    public void enQueue(E data) {
        if (isFull()) {
			System.out.println("the queue is full!");
			return;
		}
        elementData[rear]=data;
        rear=(rear+1)%elementData.length;
        
    }

    public E deQueue() {
    	if (isEmpty()) {
			return null;
		}
    	Object object=elementData[front];
    	front=(front+1)%elementData.length;
        return (E)object;
    }
}
