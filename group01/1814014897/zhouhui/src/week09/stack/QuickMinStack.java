package week09.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * findMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * 
 * @author Hui Zhou
 *
 */
public class QuickMinStack {
	Stack<Integer> stack = new Stack<Integer>();
	Stack<Integer> minStack = new Stack<Integer>();
	int min;

	public void push(int data) {
		stack.push(data);

		if (minStack.isEmpty()) {
			minStack.push(data);
			min = minStack.peek();
		} else {
			if (data <= min) {
				min = data;
				minStack.push(data);
			}
		}
	}

	public int pop() {
		if (stack.isEmpty()) {
			throw new RuntimeException("the stack is empty.");
		}
		if (stack.peek() == minStack.peek()) {
			minStack.pop();
		}
		return stack.pop();
	}

	public int findMin() {
		if (stack.isEmpty()) {
			throw new RuntimeException("the stack is empty.");
		}
		return minStack.pop();
	}
}
