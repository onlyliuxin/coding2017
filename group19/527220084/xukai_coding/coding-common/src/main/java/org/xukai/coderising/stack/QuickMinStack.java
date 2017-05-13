package org.xukai.coderising.stack;

import org.junit.Test;

import java.util.*;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {


	private TreeSet<Integer> treeSet = new TreeSet<Integer>();

	private java.util.Stack<Integer> stack = new java.util.Stack<Integer>();

	public void push(int data){
		stack.push(data);
		treeSet.add(data);
	}
	public int pop(){
		if (!stack.isEmpty()) {
			treeSet.remove(stack.peek());
			return stack.pop();
		}
		return -1;
	}
	public int findMin(){
		if (treeSet.size() > 0) {
			return treeSet.first();
		}
		return -1;
	}

	@Test
	public void testQuickMinStack(){
		QuickMinStack stack = new QuickMinStack();
		stack.push(6);
		stack.push(5);
		stack.push(4);
		stack.push(3);
		stack.push(2);
		stack.push(1);
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println("min:" + stack.findMin());
		System.out.println(stack.pop());
		System.out.println("min:" + stack.findMin());
		System.out.println(stack.pop());
		System.out.println(stack.pop());
		System.out.println("min:" + stack.findMin());
		System.out.println(stack.pop());
		System.out.println("min:" + stack.findMin());
		System.out.println(stack.pop());


	}
}
