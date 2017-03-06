package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);								//向栈顶压入元素
	}
	
	public Object pop(){
		Object o=elementData.get(elementData.size()-1);	//栈顶元素，因为栈先进后出
		elementData.remove(elementData.size()-1);		//移除栈顶元素
		return o;							
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);   //获取栈顶元素
	}
	public boolean isEmpty(){
		if(elementData.size()==0){			//根据elementData.size判断是否为空
			return true;
		}
		return false;
	}
	public int size(){
		return elementData.size();			
	}
}
