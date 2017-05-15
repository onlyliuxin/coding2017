package com.coding.basic.stack;

import com.coding.basic.Queue;

public class StackWithTwoQueues {
	
	private Queue queue1;
	private Queue queue2;
	
    public StackWithTwoQueues() {
		queue1 = new Queue();
		queue2 = new Queue();
	}

    public Boolean isEmpty(){
    	return queue1.isEmpty() && queue2.isEmpty();
    }
    
    public int size(){
    	return queue1.size() + queue2.size();
    }
    
	public void push(int data) {       
    	queue1.enQueue(data);
    }

    public int pop() {
    	if(isEmpty()){
    		throw new RuntimeException("The Stack is empty!!!");
    	}
    	
    	while(!queue1.isEmpty()){
    		Object temp = queue1.deQueue();
    		if(queue1.isEmpty()){
    			return (int)temp;
    		}else{
    			queue2.enQueue(temp);
    		}
    	}
    	
    	//queue1为空,queue2不为空
    	while(!queue2.isEmpty()){
    		Object temp = queue2.deQueue();
    		if(queue2.isEmpty()){
    			return (int)temp;
    		}else{
    			queue1.enQueue(temp);
    		}
    	}
    	
        return -1;//永远不会执行
    }
    
}
