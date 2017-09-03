package com.coding.basic.queue;

import java.util.NoSuchElementException;
import java.util.Stack;

public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    
    private Stack<E> stack2;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
    }

   
    private void moveStack1ToStack2() {
        while (!stack1.isEmpty()){
        	stack2.push(stack1.pop());
        }
            
    }


    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    
    public int size() {
        return stack1.size() + stack2.size();     
    }



    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
        if (isEmpty()) {
        	throw new NoSuchElementException("Queue is empty");
        }
        if (stack2.isEmpty()) {
        	moveStack1ToStack2();
        }
        
        return stack2.pop();
    }



 }

