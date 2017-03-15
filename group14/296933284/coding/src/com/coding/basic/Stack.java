package com.coding.basic;

/**
 * Stack 实现
 * Last In First Out
 * 第14小组 296933284
 * 
 * @author Tonnyson
 *
 */
public class Stack {
	
	private ArrayList elementData = new ArrayList();
	private int top = 0;

	/**
	 * 向栈中插入元素
	 * 
	 * @param obj
	 */
	public void push(Object obj) {
		elementData.add(obj);
		top++;
	}
	
	/**
	 * 从栈中取出元素
	 * 
	 * @return
	 */
	public Object pop() {
		return elementData.remove(--top);
	}

	/**
	 * 获取栈顶元素
	 * 
	 * @return
	 */
	public Object peek() {
		return elementData.get(top - 1);
	}
	
	/**
	 * 判断栈是否为空
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return top == 0;
	}
	
	/**
	 * 获取栈中元素个数
	 * 
	 * @return
	 */
	public int size() {
		return top;
	}
}
