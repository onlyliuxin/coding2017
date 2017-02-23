package com.coding.basic;

import java.util.NoSuchElementException;
//后进先出
public class Stack {
	private ArrayList elementData = new ArrayList();  //使用刚实现的ArrayList

	//入栈
	public void push(Object o){
		elementData.add(o);
	}

	//出栈
	public Object pop(){
		if(elementData.size() == 0) throw new NoSuchElementException("堆栈下溢");
		return elementData.remove(elementData.size()-1);
	}

	//栈顶元素
	public Object peek(){
		if(elementData.size() == 0) throw new NoSuchElementException("堆栈下溢");
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
