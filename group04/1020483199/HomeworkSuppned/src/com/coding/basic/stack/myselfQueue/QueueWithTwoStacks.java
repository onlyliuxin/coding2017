package com.coding.basic.stack.myselfQueue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;    
    private Stack<E> stack2;    

    
    public QueueWithTwoStacks() {
        stack1 = new Stack<E>();
        stack2 = new Stack<E>();
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
    	if (stack2.isEmpty()){
    		stack2 = convert(stack1);
    	}
    	
        E pop = stack2.pop();
        return pop;
    }




	private Stack<E> convert(Stack<E> stack) {
		Stack<E> newStack = new Stack<E>();
		while (!stack.isEmpty()){
			newStack.push(stack.pop());
		}
		return newStack;
	}



 }

