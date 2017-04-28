package com.github.HarryHook.coding2017.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * 
 * @author liuxin
 *
 * @param <E>
 */
public class QueueWithTwoStacks<E> {
    private Stack<E> stack1;
    private Stack<E> stack2;

    public QueueWithTwoStacks() {
	stack1 = new Stack<E>(); // 入队
	stack2 = new Stack<E>(); // 出队
    }

    public boolean isEmpty() {
	if (stack1.isEmpty() && stack2.isEmpty()) {
	    return true;
	}
	return false;
    }

    public int size() {
	return stack1.size() + stack2.size();
    }

    public void enQueue(E item) {

	stack1.push(item);
    }

    public E deQueue() {

	if (isEmpty()) {
	    throw new RuntimeException("queue is empty");
	}

	while (!(stack1.isEmpty())) {
	    stack2.push(stack1.pop());
	}
	E popItem = stack2.pop();
	while (!(stack2.isEmpty())) {
	    stack1.push(stack2.pop());
	}

	return popItem;
    }

    public static void main(String[] args) {
	QueueWithTwoStacks<Integer> stackQueue = new QueueWithTwoStacks<>();
	stackQueue.enQueue(5);
	stackQueue.enQueue(36);
	stackQueue.enQueue(1);
	stackQueue.enQueue(2);
	stackQueue.deQueue();
	stackQueue.enQueue(3);
	while (!(stackQueue.isEmpty())) {
	    System.out.println(stackQueue.deQueue());
	}

    }

}
