package com.coding.basic.stack;

import java.util.Iterator;
import java.util.Stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {
	
	Stack<Integer> elementStack = new Stack<>();
	Stack<Integer> minStack = new Stack<>();
	
	public void push(int data){
		elementStack.push(data);
		if(! minStack.isEmpty() && minStack.peek() < data){
			minStack.push(minStack.peek());
		}
		else{
			minStack.push(data);
		}
	}
	public int pop(){
		if(!elementStack.isEmpty()){
			minStack.pop();
			return elementStack.pop();
		}else{
			return Integer.MIN_VALUE;
		}
	}
	public int findMin(){
		if(minStack.peek() != null){
			return minStack.peek();
		}
		return Integer.MIN_VALUE;
	}
	public String toString(){
		Iterator<Integer> itr = elementStack.iterator();
		StringBuffer sb = new StringBuffer();
		while(itr.hasNext()){
			sb.append(itr.next() + " ");
		}
		return sb.toString();
	}
}