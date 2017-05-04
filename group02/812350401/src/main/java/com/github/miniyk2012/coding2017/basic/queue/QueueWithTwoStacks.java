package com.github.miniyk2012.coding2017.basic.queue;

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
        return stack1.isEmpty();
    }

    private void move(Stack s1, Stack s2) {
        while (!s1.empty()) {
            s2.push(s1.pop());
        }
    }
    
    public int size() {
        return  stack1.size();
    }

    public void enQueue(E item) {
        stack1.push(item);
    }

    public E deQueue() {
        move(stack1, stack2);
        E result = stack2.pop();
        move(stack2, stack1);
        return result;
    }

 }

