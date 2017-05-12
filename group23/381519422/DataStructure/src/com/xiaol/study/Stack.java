package com.xiaol.study;

import java.util.ArrayList;

/**
 * @Description TODO
 * @date 创建时间：2017年3月5日 上午12:47:15
 */
public class Stack {
	// 存数据的List
	private ArrayList elementData = new ArrayList();

	// 栈元素数量
	private int size;

	// 压栈
	public void push(Object o) {
		elementData.add(o);
		size++;
	}

	// 弹出栈顶元素
	public Object pop() {
		if (size == 0) {
			throw new RuntimeException("Stack is Empty");
		}
		Object data = elementData.remove(size - 1);
		size--;
		return data;
	}

	// 获取栈顶元素
	public Object peek() {
		return elementData.get(size - 1);
	}

	// 是否为空
	public boolean isEmpty() {
		if (size == 0) {
			return true;
		}
		return false;
	}

	// 栈大小
	public int size() {
		return size;
	}
}
