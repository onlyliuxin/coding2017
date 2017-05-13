package com.coding.basic.queue;

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
        return stack2.isEmpty();
    }


    
    public int size() {
        return stack2.size();   
    }



    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
    	while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
    	}
        return stack2.pop();
    }
    

	public static void main(String[] args) {
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks<Integer>();
		
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
		System.out.println(queue.deQueue());
    }
    
 }
