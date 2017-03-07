package com.coding.basic;

public class MyStack {
	private MyArrayList elementData = new MyArrayList();
	
	/**
	 * 入栈
	 * @param o
	 */
	public void push(Object o){	
		elementData.add(o);
	}
	
	/**
	 * 出栈
	 * @return
	 */
	public Object pop(){
		Object oldValue = elementData.get(elementData.size());
		elementData.remove(elementData.size());
		return oldValue;
	}
	/**
	 * 查看栈顶元素；
	 * @return
	 */
	public Object peek(){
		return elementData.get(elementData.size());
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
