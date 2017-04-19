package com.coding.basic;

public class Stack {
	private Object[] elementData = new Object[100];
	int nextindex =0;
	
	public void push(Object o) throws Exception{		//入栈
	if(nextindex==100){
		throw new Exception("数组越界异常！");
	}
	elementData[nextindex++]=o;
	}
	
	public Object pop() throws Exception{ //移走栈顶对象，将该对象作为函数值返回
		if(nextindex == 0){
			throw new Exception("数组越界异常！");
		}
		return elementData[--nextindex];
	}
	
	public Object peek() throws Exception{//查找栈顶对象，而不从栈中移走
		if(nextindex == 0){
			throw new Exception("数组越界异常！");
			
		}
		return elementData[nextindex-1];
	}
	public boolean isEmpty(){
		if(this.nextindex ==0){
			return true;
		}
		return false;
	}
	public int size(){
		return this.nextindex;
	}
}
