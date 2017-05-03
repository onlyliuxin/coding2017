package com.github.orajavac.coding2017.basic.queue;

public class CircleQueue <E>{
private final static int DEFAULT_SIZE = 8;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	public boolean isEmpty() {
		if (front==0&&rear==0){
			return true;
		}
		return false;
        
    }

    public int size() {
        return DEFAULT_SIZE;
    }

    

    public void enQueue(E data) {
    	if (rear==DEFAULT_SIZE&&elementData[0]==null){
    		rear = 0;
    		elementData[rear]=data;
    	}else if (elementData[rear]==null){
			elementData[rear]=data;
		}else{
			throw new RuntimeException("队列已满");
		}
    	rear++;
    }

    @SuppressWarnings("unchecked")
	public E deQueue() {
    	Object o = elementData[front];
    	elementData[front] = null;
    	front++;
    	if (front == DEFAULT_SIZE){
    		front = 0;
    	}
        return (E)o;
    }
}
