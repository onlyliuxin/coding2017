package com.dudy.learn01.data_structure.queue;

import java.util.Random;
import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 */
public class QueueWithTwoStacks<E> {
	private Stack<E> stack1;
	private Stack<E> stack2;

	public QueueWithTwoStacks() {
		stack1 = new Stack<E>();
		stack2 = new Stack<E>();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public int size() {
		return stack1.size() + stack2.size();
	}

	public void enQueue(E item) {
		stack1.push(item);
	}

	public E deQueue() {

		if (isEmpty()) {
			return null;
		}
		if (stack2.size() == 0) {
			for (int i = 0; i < stack1.size(); i++) {
				stack2.push(stack1.pop());
			}
		}

		return stack2.pop();
	}

	public static void main(String[] args) {
		QueueWithTwoStacks<Integer> queue = new QueueWithTwoStacks();
		Random r = new Random();
		int loop = r.nextInt(15);
		System.out.println("loop = " + loop);
		for (int i = 0; i < loop; i++) {
			queue.enQueue(r.nextInt(100));
		}
		System.out.println("size = " + queue.size());
		while (queue.size() > 0) {
			System.out.print(queue.deQueue() + ",");
		}
	}

}
