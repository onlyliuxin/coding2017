package com.coding.week9;


//import java.util.Stack;


import java.util.ArrayList;
import java.util.List;

/**
 * 设计一个栈，支持栈的push和pop操作，以及第三种操作findMin, 它返回改数据结构中的最小元素
 * finMin操作最坏的情形下时间复杂度应该是O(1) ， 简单来讲，操作一次就可以得到最小值
 * @author liuxin
 *
 */
public class QuickMinStack {

	List<Integer> elements = new ArrayList<>();
	private int minNumIndex = 0;

	private boolean isSmaller(int data) {
		if (elements.isEmpty()) {
			return true;
		}
		if (data < elements.get(minNumIndex)) {
			return true;
		}
		return false;
	}
	public void push(int data) {
		elements.add(data);
		if (isSmaller(data)) {
			minNumIndex = elements.size() - 1;
		}

	}

	public int pop() {
		return elements.remove(elements.size() - 1);
	}

	public int findMin() {
		return elements.get(minNumIndex);
	}
}
