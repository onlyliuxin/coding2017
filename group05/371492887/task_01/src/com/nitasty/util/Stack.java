package com.nitasty.util;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		int len=elementData.size();
		if(len==0)
			throw new EmptyStackException();
		return elementData.remove(len-1);
	}
	
	public Object peek(){
		int len=elementData.size();
		if(len==0)
			throw new EmptyStackException();
		return elementData.get(len-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
}
