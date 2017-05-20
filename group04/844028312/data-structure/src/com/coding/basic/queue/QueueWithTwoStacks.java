package com.coding.basic.queue;

import java.util.Stack;

public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;    
    private Stack<E> stack2;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

   


    public boolean isEmpty() {
       return stack1.isEmpty();
    }


    
    public int size() {
       return stack1.size();
    }



    public void enQueue(E item) {
    	stack1.push(item);  
    }

    public E deQueue() {
    	E item;
    	while(!stack1.isEmpty()){
    		if(stack1.size()==1){
    			item=stack1.pop();
    			while(!stack2.isEmpty()){
    				stack1.push(stack2.pop());
    			}
    			return item;
    		}
    		else{
    			stack2.push(stack1.pop());
    		}
    	}
        return null;
    }


 }

