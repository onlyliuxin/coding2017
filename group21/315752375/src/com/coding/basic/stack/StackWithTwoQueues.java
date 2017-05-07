package com.coding.basic.stack;

import com.coding.basic.Queue;




public class StackWithTwoQueues {
	Queue<Integer> queue1=new Queue<>();
	Queue<Integer> queue2=new Queue<>();
    public void push(int data) {       
    	while (!queue1.isEmpty()) {
			queue2.enQueue(queue1.deQueue());
		}
    	queue1.enQueue(data);
    	while (!queue2.isEmpty()) {
			queue1.enQueue(queue2.deQueue());
		}
    }

    public int pop() {
       return queue1.deQueue();
    }

    
}
