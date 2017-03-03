package com.list;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class Stack {

	ArrayList<Object> elelmentData = new ArrayList<Object>();
	
	//压入栈
	public void push(Object object){
		elelmentData.add(object);
	}
	
	//弹出栈
	public Object pop(){
		if (isEmpty()){ throw new EmptyStackException();}
		return elelmentData.remove(elelmentData.size() - 1);
	}
	
	//取栈顶元素
	public Object peek(){
		if (isEmpty()){return null;}
		return elelmentData.get(elelmentData.size() - 1);
	}
	
	public boolean isEmpty(){
		return elelmentData.isEmpty();
	}
	
	public int size(){
		if (isEmpty()){throw new EmptyStackException();}
		return elelmentData.size();
	}
}
