package queue;

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
		return size() == 0;
	}

	public int size() {
		return stack2.size() + stack1.size();
	}

	public void enQueue(E item) {
		if (stack2.size() != 0) {
			drop(stack2, stack1, stack2.size());
		}

		stack1.push(item);
	}

	private void drop(Stack<E> stack2, Stack<E> stack1, int num) {
		for (int i = 0; i < num; i++) {
			stack1.push(stack2.pop());
		}
	}

	public E deQueue() {
		if (size() <= 0) {
			return null;
		}

		if (stack1.size() != 0) {
			drop(stack1, stack2, stack1.size() - 1);
			return stack1.pop();
		}

		return stack2.pop();

	}

}
