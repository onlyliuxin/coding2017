package com.coding.basic;

/**
 * 栈(堆栈)，特点先进后出的特点
 * 
 * @author Administrator
 * 
 */
public class Stack {
	private ArrayList elementData = new ArrayList();
	private int size = 0;

	/**
	 * 在堆栈顶部中添加一个元素
	 * 
	 * @param o
	 */
	public void push(Object o) {
		elementData.add(o);
		size++;
	}

	/**
	 * 在堆栈顶部移去一个元素
	 * 
	 * @return
	 */
	public Object pop() {
		Object o = elementData.remove(size - 1);
		size--;
		return o;

	}

	/**
	 * 总是返回栈顶的元素
	 * 
	 * @return
	 */
	public Object peek() {
		return elementData.get(size - 1);
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int size() {
		return size;
	}
}
