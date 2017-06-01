package com.coding.basic.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * 
 * @author liuxin
 * @author lihao
 *
 */
public class QuickMinStack {
	private Stack<Integer> dataStack = new Stack<>();
	private Stack<Integer> minStack = new Stack<>();

	public void push(int data) {
		dataStack.push(data);
		if (minStack.isEmpty() || minStack.peek() >= data) {
			minStack.push(data);
		}
	}

	public int pop() {
		if (dataStack.isEmpty()) {
			throw new RuntimeException("empty stack");
		}
		int value = dataStack.pop();
		if (value == minStack.peek()) {
			minStack.pop();
		}
		return value;
	}

	public int findMin() {
		if (minStack.isEmpty()) {
			throw new RuntimeException("empty stack");
		}
		return minStack.peek();
	}
}
