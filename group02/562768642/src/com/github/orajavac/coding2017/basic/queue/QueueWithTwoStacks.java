package com.github.orajavac.coding2017.basic.queue;

import java.util.Stack;

public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    
    private Stack<E> stack2;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

    public boolean isEmpty() {
        return stack1.size()==0;
    }


    
    public int size() {
        return stack1.size();   
    }



    public void enQueue(E item) {
    	int len1 = stack1.size();
    	for (int i=0;i<len1;i++){
    		stack2.push(stack1.pop());
    	}
    	stack1.push(item);
    	int len2 = stack2.size();
    	for (int i=0;i<len2;i++){
    		stack1.push(stack2.pop());
    	}
    }

    public E deQueue() {
        return stack1.size()!=0?stack1.pop():null;
    }
}
