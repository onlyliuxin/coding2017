package com.hmily.learning;
public class MyStack {
	private MyArrayList elementData = new MyArrayList();
	private int size;
	public void push(Object o){	
		elementData.add(o);
		size=elementData.size();
	}
	
	public Object pop(){
		Object o=elementData.remove(this.size()-1);
		size=elementData.size();
	    return o;
	}
	
	public Object peek(){
		Object o=elementData.get(size()-1);
		return o;
	}
	public boolean isEmpty(){
		return size()==0;
	}
	public int size(){
		return elementData.size();
	}
}
