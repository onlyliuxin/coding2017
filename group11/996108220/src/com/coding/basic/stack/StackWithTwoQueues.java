package com.coding.basic.stack;

import com.coding.basic.Queue;


public class StackWithTwoQueues {
	Queue<Integer> queue1=new Queue<>();
	Queue<Integer> queue2=new Queue<>();	

    public void push(int data) {       
    	if (queue1.isEmpty()&&queue2.isEmpty()) {
			queue1.enQueue(data);
		}
    	else if (queue1.isEmpty()&&!queue2.isEmpty()) {
			queue2.enQueue(data);
		}
    	else if (!queue1.isEmpty()&&queue2.isEmpty()) {
			queue1.enQueue(data);
		}
    }

    public int pop() {
    	if (queue1.isEmpty()&&queue2.isEmpty()) {
			throw(new RuntimeException());
		}
    	else if (queue1.isEmpty()&&!queue2.isEmpty()) {
			for (int i = 0; i < queue2.size()-1; i++) {
				queue1.enQueue((int)queue2.deQueue());
			}
			return (int) queue2.deQueue();
		}
    	else if (!queue1.isEmpty()&&queue2.isEmpty()) {
			for (int i = 0; i < queue1.size()-1; i++) {
				queue2.enQueue((int)queue1.deQueue());
			}
			return (int) queue1.deQueue();
		}
		return 0;
    }

    
}
