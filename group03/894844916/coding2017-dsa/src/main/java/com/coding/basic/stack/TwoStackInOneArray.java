package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * 
 * @author liuxin
 * @author lihao
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	private int top1 = -1;
	private int top2 = 10;
	private int capacity = 10;

	/**
	 * 向第一个栈中压入元素
	 * 
	 * @param o
	 */
	public void push1(Object o) {
		expand();
		top1++;
		data[top1] = o;
	}

	/**
	 * 从第一个栈中弹出元素
	 * 
	 * @return
	 */
	public Object pop1() {
		if (top1 == -1) {
			throw new RuntimeException("empty stack1");
		}
		Object object = data[top1];
		top1--;
		return object;
	}

	/**
	 * 获取第一个栈的栈顶元素
	 * 
	 * @return
	 */
	public Object peek1() {
		if (top1 == -1) {
			throw new RuntimeException("empty stack1");
		}
		return data[top1];
	}

	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o) {
		expand();
		top2--;
		data[top2] = o;
	}

	/**
	 * 从第二个栈弹出元素
	 * 
	 * @return
	 */
	public Object pop2() {
		if (top2 == capacity) {
			throw new RuntimeException("empty stack2");
		}
		Object object = data[top2];
		top2++;
		return object;
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * 
	 * @return
	 */
	public Object peek2() {
		if (top2 == capacity) {
			throw new RuntimeException("empty stack2");
		}
		return data[top2];
	}

	private void expand() {
		if ((top1 + 1) != top2) {
			return;
		}
		int newCapacity = capacity * 2;
		Object[] data = new Object[newCapacity];
		System.arraycopy(this.data, 0, data, 0, top1 + 1);
		System.arraycopy(this.data, top1 + 1, data, capacity + top2, capacity - top1 - 1);
		capacity = newCapacity;
	}
}
