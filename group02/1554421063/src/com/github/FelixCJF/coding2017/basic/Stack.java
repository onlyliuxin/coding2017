package com.github.FelixCJF.coding2017.basic;

import java.util.EmptyStackException;

public class Stack {
	
	//存放栈内元素的容器
	private ArrayList elementData = new ArrayList();
	//记录栈内元素个数
	private int size = 0;
	
	public void push(Object o){
		elementData.add(o);
		size ++;
	}
	
	public Object pop(){
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elementData.remove(size - 1);
	}
	
	public Object peek(){
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elementData.get(size - 1);
	}
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	public int size(){
		return size;
	}
}
