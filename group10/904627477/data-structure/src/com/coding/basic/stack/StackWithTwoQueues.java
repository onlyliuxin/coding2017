package com.coding.basic.stack;

import com.coding.basic.queue.Queue;

public class StackWithTwoQueues {
	
	private Queue queue1;
	private Queue queue2;
	
	
			
    public StackWithTwoQueues() {
		super();
		queue1 = new Queue();
		queue2 = new Queue();
	}

	public void push(int data) {
    	if(queue1.isEmpty()){
    		queue2.enQueue(data);
    	}else{
    		queue1.enQueue(data);
    	}
    }
    
    public int size(){
    	return queue1.size()+queue2.size();
    }

	public int pop() {
    	if(queue1.isEmpty()){
    		return (int) getLastNode(queue2, queue1);
    	}else{
    		return (int) getLastNode(queue1, queue2);
    	}
    }
	
	public boolean isEmpty(){
		return queue1.isEmpty()&&queue2.isEmpty();
	}

	private Object getLastNode(Queue q1, Queue q2) {
		Object result = null;
		while(!q1.isEmpty()){
			result = q1.deQueue();
			if(!q1.isEmpty()){
				q2.enQueue(result);
			}
		}
		return result;
	}
	

	public String toString() {
		StringBuffer buff = new StringBuffer();
		while(!this.isEmpty()){
			if(buff.length()!=0){
				buff.append(",");
			}
			buff.append(this.pop());
		}
		return buff.toString();
	}
	
	
}
