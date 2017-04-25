package com.coding.basic.queue;

import com.coding.basic.stack.Stack;

public class QueueWithTwoStacks<E> {
	
	private Stack<E> stack1;    
    private Stack<E> stack2;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }
     
    public boolean isEmpty() {
        return (stack1.isEmpty()&&stack2.isEmpty());
    }


    
    public int size() {
        return (stack1.size()+stack2.size());   
    }

    public void enQueue(E item) {
            	
    	if(item!=null){
    		
    		while(!stack2.isEmpty()){
        		stack1.push(stack2.pop());
        	}
    		stack1.push(item);
    	}   	
    }

    public E deQueue() {
    	
    	if(isEmpty()){
    		return null;
    	}
    	while(!stack1.isEmpty()){
    		stack2.push(stack1.pop());
    	}
    	return stack2.pop();
    }



 }
