package com.coding.basic.stack;

import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author kai
 *
 */
public class QuickMinStack {
	class Node {
		private int min;
		private int data;
		
		public Node(int data){
			this.min = 0;
			this.data = data;
		}
	}
	
	Stack<Node> stack = new Stack<>();
	
	public void push(int data){
		Node node = new Node(data);
		if(stack.isEmpty()){
			node.min = data;
		} else {
			if(data < stack.peek().min){
				node.min = data;
			} else {
				node.min = stack.peek().min;
			}
		}
		stack.push(node);
	}
	public int pop(){
		return stack.pop().data;
	}
	public int findMin(){
		return stack.peek().min;
	}
	
	public static void main(String[] args){
		QuickMinStack stack = new QuickMinStack();
		stack.push(5);
		stack.push(7);
		System.out.println(stack.findMin());
		stack.push(4);
		System.out.println(stack.findMin());
		stack.pop();
		System.out.println(stack.findMin());
		stack.push(3);
		System.out.println(stack.findMin());
		
	}
}
