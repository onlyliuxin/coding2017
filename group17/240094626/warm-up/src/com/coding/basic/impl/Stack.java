package com.coding.basic.impl;

import com.coding.basic.Iterator;

/**
 * 栈的简单实现
 * @author 240094626
 */
public class Stack {
	/**长度可变的元素容器*/
	private ArrayList elementData = new ArrayList();
	
	/**
	 * 压入栈
	 * @param o
	 */
	public void push(Object o){		
		elementData.add(o);
	}
	
	/**
	 * 出栈（末尾元素），并移除
	 * @return Object
	 */
	public Object pop(){
		return elementData.remove(elementData.size()-1);
	}
	
	/**
	 * 取出栈（末尾元素），不移除
	 * @return Object
	 */
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	
	/**
	 * 判断栈是否为空
	 * @return boolean
	 */
	public boolean isEmpty(){
		return elementData.size() == 0 ? true : false;
	}
	
	/**
	 * 栈的长度，既是容器ArrayList的长度
	 * @return int
	 */
	public int size(){
		return elementData.size();
	}
	
	
	
	@Override
	public String toString() {
		return "Stack {elementData=" + elementData + "}";
	}

	public Iterator iterator(){
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator{
		int index;

		public StackIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			if(index < elementData.size()){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return elementData.get(index++);
		}
		
		
	}
}