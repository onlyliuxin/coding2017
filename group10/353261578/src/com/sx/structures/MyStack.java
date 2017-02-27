package com.sx.structures;

public class MyStack {
	private int pointer;
	private MyArrayList element;
	public MyStack() {
		element = new MyArrayList();
		pointer = -1;
	}
	
	public void push(Object o){
		pointer++;
		element.add(pointer);
	}
	public Object pop(){
		if(pointer<0)
			return -1;
		Object p = element.get(pointer);
		pointer--;
		return p;
	}
	/**
	 *只返回栈顶元素 
	 * @return
	 */
	public Object peek(){
		if(pointer<0)
			return -1;
		return element.get(pointer);
	}
	public boolean isEmpty(){
		if(pointer<0)
			return true;
		return false;
	}
	
}
