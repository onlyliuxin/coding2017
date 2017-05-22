package com.coding.basic.queue;

import java.util.NoSuchElementException;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */
public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 10;
    private final static int INCREAMENT_SIZE = 5;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		return front == rear;
        
    }

    public int size() {
        return rear > front ? rear-front : elementData.length - (front-rear);
    }

    

    public void enQueue(E data) {
        //empty
        if(this.isEmpty()){
            elementData[front] = data;
            rear++;
            return;
        }

        //oversize enlarge elementData
        if(this.size() == elementData.length){
            Object[] newElementData = new Object[elementData.length + INCREAMENT_SIZE];
            int index = 0;
            for(int i = front ; i==rear; i++){
                if(i == this.size()-1){
                    i = 0 ;
                }
                newElementData[index++] = elementData[i];
            }

            elementData = newElementData;
        }else{
            if(rear == elementData.length-1){
                rear = 0;
            }else{
                rear++;
            }
            elementData[rear] = data;
        }

    }

    public E deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue underflow");
        }
        //rear is the first element
        if(rear == 0 ){
            E data = (E)elementData[rear];
            rear = elementData.length -1 ;
            return data ;
        }else {
            return (E)elementData[rear--];
        }

    }
}
