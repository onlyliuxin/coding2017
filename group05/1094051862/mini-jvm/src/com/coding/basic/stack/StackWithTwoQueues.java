package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

public class StackWithTwoQueues {
	
	private Queue q1 = new Queue();
	private Queue q2 = new Queue();
	
    public void push(int data) {       
    	q1.enQueue(data);
    }

    public boolean isEmpty() {
    	return q1.isEmpty() && q2.isEmpty();
    }
    
    public int pop() {
    	if (isEmpty()) {
    		throw new RuntimeException("stack is empty");
    	}
    	if (q2.isEmpty()) {
    		while(!q1.isEmpty()) {
    			q2.enQueue(q1.deQueue());
    		}
    	}
    	return (int) q2.deQueue();
    }

    
}
