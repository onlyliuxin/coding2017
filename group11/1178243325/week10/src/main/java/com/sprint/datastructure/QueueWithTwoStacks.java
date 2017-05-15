package com.sprint.datastructure;

import java.util.Stack;
import java.util.NoSuchElementException;
/**
 * 用两个栈来实现一个队列
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
		if (item == null)
			return;
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

	private void moveStack1ToStack2() {
		while (!stack1.isEmpty()) {
			stack2.push(stack1.pop());
		}
	}

 }

