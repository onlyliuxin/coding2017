package com.github.chaoswang.learning.java.collection.myown;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<E> {
	private int size = 0;
	private int initialSize;
	private Object[] elements = null;//可以改为用自己的ArrayList来实现
	
	public MyStack(int initialSize){
		this.initialSize = initialSize;
		elements = new Object[initialSize];
	}
	
	//压栈
	public void push(E element){
		//达到数组上限，按initialSize扩容100%
		if(++size == elements.length){
			elements = Arrays.copyOf(elements, size + initialSize);
		}
		elements[size - 1] = element;
	}
	
	//判断栈是否为空
	public boolean empty(){
		return size <= 0? true : false;
	}
	
	public int size(){
		return size;
	}
	
	//查看栈顶元素
	public E peek(){
		if(size == 0){
			throw new EmptyStackException();
		}
		return (E)elements[size - 1];
	}
	
	//弹出栈顶元素
	public E pop(){
		if(size == 0){
			throw new EmptyStackException();
		}
		E removed = (E)elements[size -1];
		elements[size -1] = null;
		size--;
		return removed;
	}
	
	public int search(E element) {
		int index = 0;
		for (int i = 0; i < size - 1; i++) {
			if (element.equals(elements[i])) {
				index = (size -1) - i;
				break;
			}
		}
		return index;
	}
}
