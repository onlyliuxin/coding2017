package com.coding.basic.stack;

import org.junit.Test;

import com.coding.basic.array.ArrayList;

public class Stack {

	private ArrayList elementData = new ArrayList();

	/**
	 * 压栈方法
	 * 
	 * @param o
	 */
	public void push(Object o) {
		if (o != null)
			elementData.add(o);
	}

	/**
	 * 弹栈方法
	 * 
	 * @return
	 */
	public Object pop() {
		Object result = elementData.remove(elementData.size() - 1);
		return result;
	}

	/**
	 * 查看栈顶对象
	 * 
	 * @return
	 */
	public Object peek() {
		Object result = elementData.get(elementData.size() - 1);
		return result;
	}

	/**
	 * 判断是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return elementData.size() == 0;
	}

	/**
	 * 获取栈的长度
	 * 
	 * @return
	 */
	public int size() {
		return elementData.size();
	}

	/*------------------------------------------------------单元测试----------------------------------------------------*/
	/**
	 * push(Object obj)方法测试
	 */
	@Test
	public void TestPushFunction() {
		Stack stack = new Stack();
		for (int x = 0; x < 100; x++) {
			stack.push(x);
		}
	}

	/**
	 * peek()方法测试
	 */
	@Test
	public void TestPeekFunction() {
		Stack stack = new Stack();
		for (int x = 0; x < 100; x++) {
			stack.push(x);
		}
		for (int x = 0; x < stack.size(); x++) {
			System.out.println(x + ":" + stack.peek());
		}
	}

	/**
	 * pop()方法测试
	 */
	@Test
	public void TestPopFunction() {
		Stack stack = new Stack();
		for (int x = 0; x < 100; x++) {
			stack.push(x);
		}
		System.out.println("before:" + stack.size());
		for (int x = 0; x < stack.size();) {
			System.out.println(stack.pop());
		}
		System.out.println("after:" + stack.size());
	}

}
