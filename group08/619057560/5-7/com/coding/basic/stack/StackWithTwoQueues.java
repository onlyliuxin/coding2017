package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

public class StackWithTwoQueues {
	
	Queue<Integer> q1 = new Queue<>();
	Queue<Integer> q2 = new Queue<>();

    public void push(int data) {       
    	q1.enQueue(data);
    }

    public int pop() {
    	if (q1.isEmpty()) {
    		throw new IndexOutOfBoundsException();
    	}
    	while (q1.size() > 1) {
    		q2.enQueue(q1.deQueue());
    	}
    	while (!q2.isEmpty()) {
    		q1.enQueue(q2.deQueue());
    	}
    	return q1.deQueue();
    }

    
}
