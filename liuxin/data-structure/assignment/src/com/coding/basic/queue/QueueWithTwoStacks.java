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
       return false;
    }


    
    public int size() {
       return -1;
    }



    public void enQueue(E item) {
        
    }

    public E deQueue() {
        return null;
    }


 }

