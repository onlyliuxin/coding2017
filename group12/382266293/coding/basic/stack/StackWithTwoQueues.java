package stack;

import queue.Queue;

public class StackWithTwoQueues<E> {
	
	private Queue<Integer> q1 = new Queue<Integer>() ;
	private Queue<Integer> q2 = new Queue<Integer>() ;
	
	public boolean isEmpty() {
		return size() == 0;
	}
	
	public int size() {
		return q1.size() + q2.size();
	}
	
    public void push(int data) {       
    	q1.enQueue(data);
    }

    public int pop() {
    	if (size() <= 0) {
    		throw new RuntimeException("stack is empty");
    	}
    	
    	if (q1.size() >= 1) {
        	while (q1.size() > 1) {
        		q2.enQueue(q1.deQueue());
        	}
    		return q1.deQueue();
    	} else {
    		while (q2.size() > 1) {
        		q1.enQueue(q2.deQueue());
    		}
    		return q2.deQueue();
    	}

    }

    
}
