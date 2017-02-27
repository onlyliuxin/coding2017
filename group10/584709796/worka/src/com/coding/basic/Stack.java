package com.coding.basic;

public class Stack {
	//private final int MAXSIZE=20;
	private ArrayList elementData = new ArrayList();
	private int top;//栈顶指针 
			
	
	public Stack(int size) {
		super();
		this.top=0;//空栈 top=0
		
	}
	//1.入栈
	public void push(Object o){		
		top++;
		elementData.add(o);
	}
	//2.出栈
	public Object pop(){
		Object o=null;
		o=elementData.get(top);
		top--;
		return o;
	}
	//3.弹出栈顶元素
	public Object peek(){
		Object o=null;
		o=elementData.get(top);
		top--;
		return o;
	}
	//4.是否为空
	public boolean isEmpty(){
		boolean s=false;
		if(top==0)
		{
			s=true;
		}
		else s=false;
		return s;
	}
	//5.栈的大小
	public int size(){
		int size=0;
		size=top;
		return size;
	}
}
