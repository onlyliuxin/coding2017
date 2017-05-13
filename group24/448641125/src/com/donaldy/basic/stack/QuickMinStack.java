package com.donaldy.basic.stack;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */


import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**
 * 不改变栈的本身性质，
 * 在其基础上添加findMin，
 * findMin是找到最小的值，而不是返回的同时把元素弹出栈。
 */
public class QuickMinStack {

	ArrayList<Integer> arrayList = new ArrayList<Integer>();
	Stack<Integer> stack = new Stack<>();

	public void push(int data){

		this.stack.push(data);

		this.arrayList.add(data);

		sort();

	}

	private void sort() {
		Collections.sort(this.arrayList);
	}

	public int pop(){

		int oldData = this.stack.pop();

		this.arrayList.remove(new Integer(oldData));

		return oldData;
	}

	public int findMin(){

		if (this.arrayList.isEmpty()) {
			throw new IndexOutOfBoundsException("stack is empty.");
		}

		return this.arrayList.get(0);
	}



}
