package com.github.qq809203042.coding2017.basic.structures;

import java.util.ArrayList;

public class MyStack {
	private ArrayList<Object> elementData = new ArrayList<Object>();
//	栈指针
	private int pos;
//	压栈
	public Object push(Object obj) {
		elementData.add(obj);
		pos++;
		return obj;
	}
//	弹栈
	public Object pop() {
		if(isEmpty()){
			throw new StackOverflowError("栈溢出");
		}
		return elementData.remove(--pos);
	}
//	返回栈顶对象
	public Object peek() {
		int topIndex = pos -1;
		return elementData.get(topIndex);
	}

	public boolean isEmpty() {
		return pos == 0;
	}

	public int size() {
		return pos;
	}
	@Override
	public String toString() {
		return elementData.toString();
	}
	
	
}
