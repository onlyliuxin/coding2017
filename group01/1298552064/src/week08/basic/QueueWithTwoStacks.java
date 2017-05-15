package week08.basic;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列
 * 
 * @author gallenzhang
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
		return stack1.size() == 0;
	}

	public int size() {
		return stack1.size();
	}

	public void enQueue(E item) {
		stack1.push(item);
	}

	public E deQueue() {
		if(stack1.isEmpty()){
			return null;
		}
		while(!stack1.isEmpty()){
			stack2.push(stack1.pop());
		}
		E e = stack2.pop();
		while(!stack2.isEmpty()){
			stack1.push(stack2.pop());
		}
		return e;
	}
}
