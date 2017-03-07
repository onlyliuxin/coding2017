package com.coding.basic;

import java.util.*;

public class MyStack {
	
	
     MyArrayList elementData=new MyArrayList();
	
     //入栈
	public void push(Object o){
		elementData.add(o);
	}
	
	//出栈
	public Object pop(){
		
		Object element =elementData.get(elementData.size() - 1);
		elementData.remove(elementData.size()-1);
		return element;
	}
	
	//获取栈顶元素
	public Object peek(){
		int len =elementData.size();
		if(len == 0)
			throw new EmptyStackException();
		Object element =elementData.get(len - 1);
		return element;
	}
	
	public int size(){
		return elementData.size();
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}
	
	public boolean empty(){
		return elementData.isEmpty();
	}
	
}
