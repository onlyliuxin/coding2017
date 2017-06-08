package com.coding.basic.stack;

import java.util.LinkedList;
import java.util.Queue;

public class StackWithTwoQueues {
	Queue<Integer> queue1 = new LinkedList<>();
	Queue<Integer> queue2 = new LinkedList<>();

	public void push(int data) {
		if (queue2.isEmpty()) {
			queue1.add(data);
			return;
		}
		queue2.add(data);
	}

	public int pop() {
		if (queue1.isEmpty() && queue2.isEmpty()) {
			throw new RuntimeException("empty queue");
		}
		if (!queue1.isEmpty()) {
			while (queue1.size() > 1) {
				queue2.add(queue1.poll());
			}
			return queue1.poll();
		}
		while (queue2.size() > 1) {
			queue1.add(queue1.poll());
		}
		return queue2.poll();
	}

}
