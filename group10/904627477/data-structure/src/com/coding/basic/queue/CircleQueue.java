package com.coding.basic.queue;

import java.lang.reflect.Array;
import java.util.Arrays;

import com.coding.util.ArrayUtil;

public class CircleQueue <E> {
	
	private final static int DEFAULT_SIZE = 3;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = -1;  
	//队尾  
	private int rear = -1;
	
	public boolean isEmpty() {
		return size()==0;
    }
	
	private boolean isFull(){
		return size()==elementData.length;
	}

    public int size() {
    	if(front==-1&&rear==-1){
    		return 0;
    	}
    	if(rear>=front){
    		return rear-front+1;
    	}else{
    		return elementData.length-(front-rear-1);
    	}
    	
    }

    public void enQueue(E data) {
    	if(isFull()){
    		elementData = ArrayUtil.grow(elementData, 1);
    	}else{
    		if(isEmpty()){
    			front = 0;
    		}
    		rear++;
    		if(rear>=elementData.length){
    			rear = 0;
    		}
    		elementData[rear] = data;
    	}
    }

    public E deQueue() {
    	if(isEmpty()){
    		return null;
    	}else{
    		Object result = elementData[front];
    		elementData[front] = null;
    		if(size()==1){
    			front = -1;
    			rear = -1;
    		}else{
    			front++;
    			if(front>=elementData.length){
    				front = 0;
    			}
    		}
    		return (E) result;
    	}
    }

	public String toString() {
		StringBuffer buff = new StringBuffer();
		while(!this.isEmpty()){
			if(buff.length()!=0){
				buff.append(",");
			}
			buff.append(this.deQueue());
		}
		return buff.toString();
	}
    
    
}