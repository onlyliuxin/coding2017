package com.github.ipk2015.coding2017.basic.stack;

import com.github.ipk2015.coding2017.basic.queue.Queue;

public class StackWithTwoQueues {
	
	private Queue queue1 = new Queue();
	private Queue queue2 = new Queue();

    public void push(Object data) {       
    	queue1.enQueue(data);
    }

    public Object pop() {
    	if(queue1.size() == 1){
    		return queue1.deQueue();
    	}
    	if(queue2.isEmpty()){
    		moveElements(queue1,queue2);
    	}
       return queue2.deQueue();
    }
    
    private void moveElements(Queue oirQueue,Queue aimQueue){
    	if(oirQueue.isEmpty()){
    		return;
    	}else{
    		Object element = oirQueue.deQueue();
    		moveElements(oirQueue,aimQueue);
    		aimQueue.enQueue(element);
    	}
    }
     
    public boolean isEmpty(){
		return queue1.isEmpty() && queue2.isEmpty();
	}
    
    public int size(){
		return queue1.size()+queue2.size();
	}
    
}
