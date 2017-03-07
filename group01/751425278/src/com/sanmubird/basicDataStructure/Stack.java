package com.sanmubird.basicDataStructure;

public class Stack {
	
	private Object[] arrayStack ;//
	private int maxSize ;	//栈容量
	private int top ;		//栈指针；
	
	public Stack(int initialSize){
		if(initialSize >= 0 ){
			this.arrayStack = new Object[initialSize];
			this.maxSize = initialSize ;
			this.top = -1 ;
		}else{
			throw new RuntimeException("初始化的大小不能小于0"+initialSize);
		}
	}
	
	// 进栈，进栈的第一个元素的top = 0 ；
	public void push(Object o){	
		if(top == maxSize -1 ){
			throw new RuntimeException("栈已满，无法将元素插入栈");
		}else{
			arrayStack[++top] = o ;
		}
	}
	
	public Object pop(){
		if(top == -1){
			throw new RuntimeException("栈为空！");
		}else{
			return arrayStack[top--];	
		}
	}
	
//	查看栈顶元素，但是不移除
	public Object peek(){
		if(top == -1){
			throw new RuntimeException("栈为空！");
		}else{
			return arrayStack[top];	
		}
	}
	public boolean isEmpty(){
		return top == -1 ? true : false;
	}
	public int size(){
		return arrayStack.length;
	}
}