package com.github.HarryHook.coding2017.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * 
 * @author HarryHook
 *
 */
public class QuickMinStack {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void push(int data) {

	s1.push(data);

	if (s2.isEmpty()) {
	    s2.push(data);
	} else if (data > s2.peek()) {
	    s2.push(s2.peek());
	} else {
	    s2.push(data);
	}

    }

    public int pop() {
	if (s1.isEmpty()) {
	    throw new RuntimeException("stack is empty");
	}
	s2.pop();
	return s1.pop();
    }

    public int findMin() {
	if (s2.isEmpty()) {
	    throw new RuntimeException("stack is empty");
	}
	return s2.peek();
    }

    public static void main(String[] args) {
	QuickMinStack stack = new QuickMinStack();
	stack.push(5);
	stack.push(3);
	stack.push(2);
	stack.push(4);
	stack.push(1);
	System.out.println("findMin: " + stack.findMin());
	System.out.println("pop: " + stack.pop());
	System.out.println("findMin: " + stack.findMin());
	System.out.println("pop: " + stack.pop());
	System.out.println("findMin: " + stack.findMin());
	System.out.println("pop: " + stack.pop());
	System.out.println("findMin: " + stack.findMin());
	System.out.println("pop: " + stack.pop());
	System.out.println("findMin: " + stack.findMin());
	System.out.println("pop: " + stack.pop());
	System.out.println("findMin: " + stack.findMin());
    }
}
