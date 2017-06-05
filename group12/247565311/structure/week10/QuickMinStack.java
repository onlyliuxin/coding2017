package structure.week10;

import structure.week1.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 */
public class QuickMinStack {
	private Stack<Integer> stack;
	private Stack<Integer> minStack;
	public QuickMinStack(){
		stack = new Stack<Integer>();
		minStack = new Stack<Integer>();
	}
	public void push(int data){
		stack.push(data);
		if(data<=minStack.peek().intValue()) minStack.push(data);
	}
	public int pop(){
		int val = stack.pop();
		if(val == minStack.peek().intValue()) minStack.pop();
		return val;
	}
	public int findMin(){
		return minStack.peek();
	}
}
