package com.github.ipk2015.coding2017.basic;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
		Object data=elementData.remove(size()-1);
		return data;
	}
	
	public Object peek(){
		if(isEmpty()){
			throw new EmptyStackException();
		}
		return elementData.get(size()-1);
	}
	public boolean isEmpty(){
		return size()==0;
	}
	public int size(){
		return elementData.size();
	}
}
