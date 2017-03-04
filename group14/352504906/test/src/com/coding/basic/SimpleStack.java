package com.coding.basic; 

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @Description 简单实现stack
 */
public class SimpleStack {
	private Object[] elementData = new Object[10];
	private int size;
	/**
	 * 往栈顶添加新的元素
	 * @param o 要添加的元素
	 */
	public void push(Object o){
		//扩容
		if(elementData.length < size +1){
			grow(size+1);
		}
		elementData[size++] = o;
	}
	/**
	 * 数组扩容
	 * @param capacity 数组实际长度
	 */
	private void grow(int capacity) {
		int oldCapacity = elementData.length;
		int newCapacity = oldCapacity *2;//扩容2倍
		if(newCapacity < capacity){
			newCapacity = capacity;
		}
		elementData = Arrays.copyOf(elementData, newCapacity);
	}
	/**
	 * 移除并返回栈顶元素
	 * @return Object 返回该移除的元素
	 */
	public Object pop(){
		Object o = peek();
		removeElement(size-1);
		return o;
	}
	/**
	 * 移除栈顶元素
	 * @param length 栈的长度
	 */
	private void removeElement(int length) {
		elementData = Arrays.copyOf(elementData, length);
		size --;
	}
	/**
	 * 返回栈顶元素
	 * @return Object 栈顶元素
	 */
	public Object peek(){
		if(size == 0){
			throw new EmptyStackException();
		}
		return elementData[size-1];
	}
	/**
	 * 查询并返回栈的长度
	 * @return int 栈的长度
	 */
	public int size(){
		return this.size;
	}
	/**
	 * 判断是否为空栈
	 * @return boolean
	 */
	public boolean isEmpty(){
		return size==0;
	}
}
