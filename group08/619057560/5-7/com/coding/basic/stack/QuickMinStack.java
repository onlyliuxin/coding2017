package com.coding.basic.stack;

import com.coding.basic.BinaryTreeNode;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	private Stack s = new Stack();
	private BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
	
	public void push(int data){
		s.push(data);
		root.insert(data);
	}
	public int pop(){
		Integer o = (Integer)s.pop();
		root.remove(o);
		return o;
	}
	public int findMin(){
		return root.findMinNode().getData();
	}
}
