package com.sprint.datastructure;

import java.util.Stack;
import java.util.EmptyStackException;
/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 *
 */
public class QuickMinStack {

	Stack<Integer> stack = new Stack<>();
	int minValue;

	public void push(int data){
		if (isEmpty()) {
			minValue = data;
		}
		if (minValue > data) {
			minValue = data;
		}
		stack.push(data);
	}

	public int pop(){
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		int top = stack.pop();
		if (top == minValue && !isEmpty()) {
			minValue = getMinValue(stack);
		}
		return top;
	}

	public int findMin(){
		return minValue;
	}

	private boolean isEmpty() {
		return stack.size() == 0; 
	}

	private int getMinValue(Stack<Integer> stack) {
		Stack<Integer> stack1 = stack;	
		int min = stack1.pop();
		while (!isEmpty()) {
			int value = stack1.pop();
			if (min > value) {
				min = value;
			}
		}
		return min;
	}
}

