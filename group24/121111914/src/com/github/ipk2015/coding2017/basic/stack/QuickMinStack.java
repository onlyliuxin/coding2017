package com.github.ipk2015.coding2017.basic.stack;

import java.util.HashMap;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	private Stack normalStack = new Stack();
	private Stack minStack = new Stack();
	public void push(int data){
		normalStack.push(data);
		if(minStack.isEmpty()){
			minStack.push(data);
		}else{
			if(data <= (Integer)minStack.peek()){
				minStack.push(data);
			}
		}
	}
	public int pop(){
		int element = (Integer)normalStack.pop();
		if(element == (Integer)minStack.peek()){
			minStack.pop();
		}
		return element;
	}
	public int peek(){
		return (Integer)normalStack.peek();
	}
	public int findMin(){
		return (Integer)minStack.peek();
	}
}
