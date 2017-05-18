package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

public class StackWithTwoQueues {
	
	/*private QueueWithTwoStacks<Integer> first;
	private QueueWithTwoStacks<Integer> end;*/
	private Queue first;
	private Queue end;
	
	public StackWithTwoQueues(){
		first = new Queue();
		end = new Queue();
	}
	
    public void push(int data) {       
    	first.enQueue(data);
    }

    public int pop() {
    	int result = 0;
    	if(first.isEmpty()){
    		throw new NullPointerException("空栈!");
    	}
    	while(!first.isEmpty()){
    		if(first.size()==1){
    			result = (Integer)first.deQueue();
    		}else{
    			end.enQueue(first.deQueue());
    		}
    	}
    	while(!end.isEmpty()){
    		first.enQueue(end.deQueue());
    	}
        return result;
    }

    
}
