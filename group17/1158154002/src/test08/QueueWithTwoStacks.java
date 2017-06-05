package test08;

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
	private int size;

	public QueueWithTwoStacks() {
		stack1 = new Stack<E>();
		stack2 = new Stack<E>();
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}

	public void enQueue(E item) {
		stack1.push(item);
		size++;
	}

	public E deQueue() {
		E re = null;
		if (!stack2.empty()) { // 如果栈2不是空的，那么把最上面那个取出来
			re = stack2.pop();
		} else {
			// 如果栈2是空的，就把栈1里的数一个个取出来，放到栈2里
			while (!stack1.empty()) {
				re = stack1.pop();
				stack2.push(re);
			}
			// 栈2里有数之后，再次把里面的数取出来
			if (!stack2.empty()) {
				re = stack2.pop();
			}
		}
		size--;
		return re;
	}
}