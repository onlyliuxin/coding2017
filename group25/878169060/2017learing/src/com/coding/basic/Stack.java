package com.coding.basic;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();

	//把项压入堆栈顶部
	public void push(Object o) {
		elementData.add(o);
	}

	// 移除堆栈顶部的对象，并作为此函数的值返回该对象
	public Object pop() {
		// 如果栈为空 抛出异常
		if (elementData.size() == 0) {
			throw new EmptyStackException();
		}
		// 返回移除堆栈顶部的对象
		return elementData.remove(elementData.size() - 1);
	}

	// 查看堆栈顶部的对象，但不从堆栈中移除它
	public Object peek() {
		// 如果栈为空 抛出异常
		if (elementData.size() == 0) {
			throw new EmptyStackException();
		}
		// 返回堆栈顶部的对象
		return elementData.get(elementData.size() - 1);
	}

	// 测试堆栈是否为空。
	public boolean isEmpty() {
		// return elementData.size()>0?false:true;
		return elementData.size() == 0;
	}

	// 获取栈的大小
	public int size() {
		return elementData.size();
	}
}
