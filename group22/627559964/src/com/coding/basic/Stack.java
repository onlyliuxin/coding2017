package com.coding.basic;

/**
 * 自定义stack
 * 
 * @author xiongrui233
 *
 */
public class Stack {
	
	//元素集合
	private ArrayList elementData = new ArrayList();

	/**
	 * 向栈顶压入元素
	 * @param o
	 */
	public void push(Object o) {
		elementData.add(0,o);
	}

	/**
	 * 获得栈顶元素,并移除栈里该元素
	 * @return obj
	 */
	public Object pop() {
		Object obj = elementData.get(0);
		elementData.remove(0);
		return obj;
	}

	/**
	 * 获得栈顶元素,不移除栈里该元素
	 * @return obj
	 */
	public Object peek() {
		return elementData.get(0);
	}

	/**
	 * 判断该栈是否为空
	 * @return true/false
	 */
	public boolean isEmpty() {
		if (elementData.size() != 0) {
			return false;
		}
		return true;
	}

	/**
	 * 获得栈的大小
	 * @return size
	 */
	public int size() {
		return elementData.size();
	}
}