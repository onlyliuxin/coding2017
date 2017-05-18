package com.sprint.datastructure;

import java.util.EmptyStackException;
public class StackWithTwoQueues {
	
	Queue queue1;
	Queue queue2;

	public StackWithTwoQueues() {
		queue1 = new Queue();
		queue2 = new Queue();

	}

	public void push(int data) {
		queue1.enQueue(data);		
	}

	public int pop() {
		if (queue1.isEmpty()) {
			throw new EmptyStackException();
		}
		int top = getTop();
		return top;
	}

	private int getTop() {
		int length = queue1.size() - 1; //小bug:queue.size()
		for (int i = 0; i < length; i++) {
			queue2.enQueue(queue1.deQueue());

		}
		int top = (int)queue1.deQueue();
		queue2ToQueue1();
		return top;
	}

	private void queue2ToQueue1() {
		while (!queue2.isEmpty()) {
			queue1.enQueue(queue2.deQueue());
		}
	}
}
