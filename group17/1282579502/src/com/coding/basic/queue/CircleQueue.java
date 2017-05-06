package com.coding.basic.queue;

import java.util.Iterator;

/**
 * 用数组实现循环队列
 * @author liuxin
 *
 * @param <E>
 */

/*    r   f  
 *  1,2,3,4,5
 */
public class CircleQueue <E>{
	
	private final static int DEFAULT_SIZE = 5;
	
	//用数组来保存循环队列的元素
	private Object[] elementData = new Object[DEFAULT_SIZE] ;
	
	//队头
	private int front = 0;  
	//队尾  
	private int rear = 0;
	
	int size = 0;


    public int size() {
      return size;
    }

    

    public void enQueue(E data) {
    	if(isFull()){
    		System.err.println("The queue is FULL. ");
    		return;
    	}
    	else{
	        elementData[rear] = data;
	        rear= (rear + 1) % DEFAULT_SIZE ;
	        size ++;
    	}
    }
    
    private boolean isFull(){
    	if(size == DEFAULT_SIZE){
    		return true;
    	}
    	return false;
    }
    
    private boolean isEmpty(){
    	return size == 0 ? true : false;
    }

    public E deQueue() {
       E item = null;
       if(isEmpty()){
    	   System.err.println("The queue is Empty.");
    	   return null;
       }
       else{
    	   item = (E)elementData[front];
    	   elementData[front] = null;
    	   front = front + 1 % DEFAULT_SIZE;
    	   size --;
       }
       return item;
    }
    
    public void printInfo(){
    	for(int i = 0; i< elementData.length; i++){
    		if(elementData[i] == null){
    			System.out.print("null ");
    		}
    		else{
    			System.out.print(i+":" + elementData[i]+ " ");
    		}
    	}
    	System.out.println("front: " + front + " rear: " + rear);
    }
    
    public String toString(){
    	Iterator<E> itr = this.Iterator();
    	StringBuffer sb = new StringBuffer();
    	while(itr.hasNext()){
    		sb.append(itr.next() + " ");
    	}
    	return sb.toString();
    }
    
    Iterator<E> Iterator(){
    	return new circleQueueIterator();
    }
    
    private class circleQueueIterator implements Iterator<E>{

    	int cursor = front;
    	int tmpSize = size;
    	
		@Override
		public boolean hasNext() {
			if(tmpSize> 0  ){
				return true;
			}
			return false;
		}

		@Override
		public E next() {
			E item = (E)elementData[cursor];
			cursor = (cursor + 1) % DEFAULT_SIZE;
			tmpSize --;
			return item;
		}
    	
    }
}
