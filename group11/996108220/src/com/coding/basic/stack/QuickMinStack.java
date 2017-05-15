package com.coding.basic.stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	Stack stack=null;
	Stack min=null;
	public QuickMinStack() {
		stack=new Stack();
		min=new Stack();
	}
	public void push(int data){
		if (stack.isEmpty()) {
			int copy=data;
			push(data);
			push(copy);
			
		}
		else {
			if (data<(int)min.peek()) {
				int copy=data;
				push(data);
				push(copy);
			}
			else {
				stack.push(data);
				min.push(min.peek());
			}
		}
	}
	public int pop(){
		if (stack.isEmpty()) {
			throw(new NullPointerException());
		}
		min.pop();
		return (int) stack.pop();
	}
	public int findMin(){
		return (int) min.peek();
	}
}
