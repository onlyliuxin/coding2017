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
	List<Integer> values=new java.util.ArrayList<>();
	int min=0;
	public void push(int data){
		values.add(data);
		stack.push(data);
	}
	public int pop(){
		Integer integer=stack.pop();
		values.remove(integer);
		if(integer==min){
		Collections.sort(values);
		min=values.get(0);
		}
		return stack.pop();
	}
	public int findMin(){
		return min;
	}
}
