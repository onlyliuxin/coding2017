package com.coding.basic;

public class Stack {
	/**
	 * 当前栈的大小
	 */
	private int size;
	
	private ArrayList elementData = new ArrayList();
	/**
	 * 压栈
	 * @param o
	 */
	public void push(Object o){
		elementData.add(o);
		size++;
	}
	/**
	 * 出栈
	 * @return
	 */
	public Object pop(){
		return elementData.remove(--size);
	}
	/**
	 * 返回栈顶元素
	 * @return
	 */
	public Object peek(){
		return elementData.get(size-1);
	}
	public boolean isEmpty(){
		return size==0;
	}
	public int size(){
		return size;
	}
}
