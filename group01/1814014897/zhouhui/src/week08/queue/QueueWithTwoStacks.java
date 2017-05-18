package week08.queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * 
 * @author Hui Zhou
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

	public int size() {
		return stack1.size();
	}

	public void enQueue(E item) {
		stack1.push(item);
	}

	public E deQueue() {
		int stack1Size = stack1.size();
		for (int i = 0; i < stack1Size; i++) {
			stack2.push(stack1.pop());
		}
		E deQueue = stack2.pop();
		int stack2Size = stack2.size();
		for (int i = 0; i < stack2Size; i++) {
			stack1.push(stack2.pop());
		}
		return deQueue;
	}

}
