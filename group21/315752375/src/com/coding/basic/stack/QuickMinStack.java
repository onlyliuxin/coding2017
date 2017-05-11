package com.coding.basic.stack;

import java.util.Collections;
import java.util.List;
import java.util.Stack;

import com.coding.basic.ArrayList;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author gdw
 *
 */
public class QuickMinStack {
	Stack<Integer> stack=new Stack<>();
	Stack<Integer> values=new Stack<>();
	int min;
	public boolean isEmpty() {
		return stack.isEmpty();
	}
	public void push(int data){
		if(stack.isEmpty()){
			values.push(data);
		}else{
			if(data<=values.peek())
				values.push(data);
		}
		stack.push(data);
	}
	public int pop(){
		int pop=stack.pop();
		if(pop==values.peek())values.pop();
		return pop;
	}
	public int findMin(){
		return values.peek();
	}
}
