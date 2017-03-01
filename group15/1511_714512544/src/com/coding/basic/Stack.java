package com.coding.basic;

import java.util.NoSuchElementException;
//LIFO
public class Stack {
	private ArrayList elementData = new ArrayList();  //使用刚实现的ArrayList

	//入栈
	public void push(Object o){
		elementData.add(o);
	}

	//出栈
	public Object pop(){
		if(elementData.size() == 0) throw new NoSuchElementException("Stack Underflow");
		return elementData.remove(elementData.size()-1);
	}

	//栈顶元素
	public Object peek(){
		if(elementData.size() == 0) throw new NoSuchElementException("Stack Underflow");
		return elementData.get(elementData.size()-1);
	}

	//是否为空
	public boolean isEmpty(){
		return elementData.size() == 0;
	}

	//栈内元素个数
	public int size(){
		return elementData.size();
	}

}
