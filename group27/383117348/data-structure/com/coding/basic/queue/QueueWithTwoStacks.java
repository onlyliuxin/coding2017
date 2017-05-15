package com.coding.basic.queue;

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
		stack1 = new Stack<E>();
		stack2 = new Stack<E>();
	}

	public boolean isEmpty() {
		return stack1.size() == 0 && stack2.size() == 0;
	}

	public int size() {
		if (isEmpty()) {
			return 0;
		} else {
			return stack1.size();
		}
	}

	public void enQueue(E item) {
		if (item == null) {
			throw new NullPointerException();
		}
		stack1.push(item);
	}

	public E deQueue() {
		if (stack1.isEmpty()) {
			throw new RuntimeException("队列为空");
		}
		while (stack1.size() > 0) {
			stack2.push(stack1.pop());
		}
		E item = stack2.pop();
		while (stack2.size() > 0) {
			stack1.push(stack2.pop());
		};
		return item;
	}

}
