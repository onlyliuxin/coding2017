package com.coding.basic;

public class Queue {
    private int size;
    
    //头标志位
    private int head;
    
    //尾标志位
    private int tail;
    
    private Object[] queue;

    public Queue() {
        this.queue = new Object[10];
        this.size = 0 ;
        this.head = 0;
        this.tail = 0;
    }

    public void enQueue(Object o) {
    	if(isFull()){
    		resize();
    		head=0; 
    	}
    	int newtail = (head + size )% queue.length;
    	queue[newtail]=o;
    	size ++;
    }

    public Object deQueue() {
    	if(isEmpty())
    		throw new IndexOutOfBoundsException("队列为空!");
    	Object old = queue[head];
    	queue[head] =null;
    	head = (head + 1)% queue.length;
    	size --;
    	return old;
    	
    }
    
    public Object getHead(){
		return head;
    }
    
    public Object getTail(){
 		return tail;
     }
     
    
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
    	int diff = tail - head;
        return diff;
    }
    
    //
    public boolean isFull(){
    	return size == queue.length;
    }
    
    //扩容
    public void resize(){
    	Object[] newQueue = new Object[queue.length * 2];
    	System.arraycopy(queue, 0, newQueue, 0, size);
    	this.queue = newQueue;
    	newQueue = null;
    }
}
