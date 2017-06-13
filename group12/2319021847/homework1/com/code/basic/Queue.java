package com.coding.basic;

public class Queue {
	private int size;
	
	private int head;
	
	private int tail;
	
	private Object[] queue;
	
	public Queue()
	{
		this.size = 0;
		this.head = 0;
		this.tail = 0;
	}
	
	 public void enQueue(Object o) {
		 if(isFull())
		 {
			 resize();
		 }
		 int newtail = (head+size)%queue.length;
		 queue[newtail] = o;
	     size++;
	 }
	
    public Object deQueue() {
    	if(isEmpty())
    	{
    		return null;
    	}
    	Object oldHead = queue[head];
    	head = (head-1)%queue.length;
    	size--;
    	return oldHead;
    }
    
    public int  getHead(){
		return head;
    }
    
    public int  getTail(){
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
    
 
    public void resize(){
    	Object[] newQueue = new Object[2*(queue.length)];
    	System.arraycopy(queue, 0, newQueue, 0, size);
    	this.queue  = newQueue;
    	newQueue = null;
    }
}
