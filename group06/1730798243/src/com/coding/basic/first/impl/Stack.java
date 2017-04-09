package com.coding.basic.first.impl;

/**
 * 基本数据结构-栈
 * @author Pxshuo
 *
 */

public class Stack {
	
	private ArrayList elementData = new ArrayList();
	
	/**
	 * 使一个元素入栈
	 * @param o 将要入栈的元素
	 */
	public void push(Object o){		
		elementData.add(elementData.size(), o);
	}
	
	/**
	 * 使一个元素出栈
	 * @return 返回出栈的元素
	 */
	public Object pop(){
		return size() == 0 ? null : elementData.remove(elementData.size() - 1);
	}
	
	/**
	 * 获得栈顶元素
	 * @return 返回栈顶元素
	 */
	public Object peek(){
		return elementData.size() == 0 ? null : elementData.get(elementData.size() - 1);
	}
	
	/**
	 * 查看栈是否为空
	 * @return 空的话返回true
	 */
	public boolean isEmpty(){
		return size() == 0 ? true : false;
	}
	
	/**
	 * 查看栈中元素的个数
	 * @return 返回栈中的个数
	 */
	public int size(){
		return elementData.size();
	}
	
}
