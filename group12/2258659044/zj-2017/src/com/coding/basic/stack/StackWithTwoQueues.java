package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

/**
 * 
 * @author zj
 *
 * @param <E>
 */
public class StackWithTwoQueues<E> {
	

	private final Queue<E> q1 = new Queue<E>();
	
	private final Queue<E> q2 = new Queue<E>();
	
    public void push(E data) {       

    	q1.enQueue(data);
    }

    public E pop() {
       
    	while(q1.size()>1){
    		q2.enQueue(q1.deQueue());
    	}
    	return q1.deQueue();
    }

    public int size(){
    	return q1.size()+q2.size();
    }
    
    public boolean isEmpty(){
    	return q1.size()+q2.size()==0;
    }
}
