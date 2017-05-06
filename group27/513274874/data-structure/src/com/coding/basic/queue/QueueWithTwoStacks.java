package com.coding.basic.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
    //store data
	private Stack<E> stack1;

    //temp memory
    private Stack<E> stack2;    

    private int size;
    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
        size = 0 ;
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }


    
    public int size() {
        return size;
    }



    public void enQueue(E item) {
        stack1.push(item);
        size++;
    }

    public E deQueue() {
        if(this.isEmpty() ) return null;

        while(!stack1.isEmpty()){
            stack2.push(stack1.pop());
        }

        E data = stack2.pop();

        //put back the data
        while(!stack1.isEmpty()){
            stack1.push(stack2.pop());
        }

        size--;
        return data;
    }


 }

