package com.coding.basic.stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	Stack<Integer> s = new Stack<Integer>();
	Stack<Integer> m = new Stack<Integer>();
	public void push(int data){
		s.push(data);
		if(m.isEmpty()) {
			m.push(data);
		}
		else if (data <= (int)m.peek()) {
			m.push(data);
		}
	}
	public int pop(){
		if (m.isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		int x = (int) m.pop();
		if (x==(int)m.peek()) {
			m.pop();
		}
		return x;
	}
	public int findMin(){
		if (m.isEmpty()) {
			throw new RuntimeException("stack is empty");
		}
		return (int) m.peek();
	}
}
