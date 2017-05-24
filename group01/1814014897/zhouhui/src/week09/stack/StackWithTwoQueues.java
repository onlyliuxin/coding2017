package week09.stack;

import week08.queue.Queue;

public class StackWithTwoQueues<T> {
	private Queue<T> queue1 = new Queue<T>();
	private Queue<T> queue2 = new Queue<T>();

	public boolean isEmpty() {
		return queue1.isEmpty();
	}

	public int size() {
		return queue1.size();
	}

	public void push(T data) {
		queue1.enQueue(data);
	}

	public T pop() {
		if (queue1.isEmpty()) {
			throw new RuntimeException("the stack is empty.");
		}
		int queue1Size = queue1.size();
		for (int i = 0; i < queue1Size - 1; i++) {
			queue2.enQueue(queue1.deQueue());
		}
		T popValue = queue1.deQueue();
		while (!queue2.isEmpty()) {
			queue1.enQueue(queue2.deQueue());
		}
		return popValue;
	}

}
