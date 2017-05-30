package org.xukai.coderising.stack;

import org.junit.Test;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {

	Object[] data = new Object[5];

	private int s1Pos = -1;

	private int s2Pos = data.length;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if (s1Pos == s2Pos) {
			doubleCapcity(data, s1Pos, s2Pos);
		}
		s1Pos++;
		data[s1Pos] = o;
	}

	private void doubleCapcity(Object[] data, int s1Pos, int s2Pos) {
		Object[] newData = new Object[2 * data.length];
		System.arraycopy(data, 0, newData, 0, s1Pos + 1);
		System.arraycopy(data, s2Pos, newData, data.length + s2Pos, data.length - s2Pos);
		this.s2Pos = data.length + s2Pos;
		this.data = newData;
	}

	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if (s1Pos > -1) {
			Object o = data[s1Pos];
			s1Pos--;
			return o;
		}
		return null;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	public Object peek1(){
		if (s1Pos > -1) {
			return data[s1Pos];
		}
		return null;
	}

	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if (s1Pos == s2Pos - 1) {
			doubleCapcity(data, s1Pos, s2Pos);
		}
		s2Pos--;
		data[s2Pos] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if (s2Pos < data.length) {
			Object o = data[s2Pos];
			s2Pos++;
			return o;
		}
		return null;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if (s2Pos < data.length) {
			return data[s2Pos];
		}
		return null;
	}

	@Test
	public void testTwoStackInOneArray(){
		TwoStackInOneArray stack = new TwoStackInOneArray();
		System.out.println(stack.peek1());
		System.out.println(stack.peek2());
		System.out.println(stack.pop1());
		System.out.println(stack.pop2());

		stack.push1(1);
		stack.push1(2);
		stack.push2(6);
		stack.push2(5);
		stack.push2(4);
		stack.push2(3);
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
		System.out.println(stack.pop1());
		System.out.println(stack.pop2());
		System.out.println(stack.pop2());
		System.out.println(stack.pop2());
		System.out.println(stack.pop2());

	}
	
}
