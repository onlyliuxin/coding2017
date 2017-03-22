package com.zzl.util;

import java.util.EmptyStackException;

public class Stack {

	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		Object o;
		int len = elementData.size();
		
		o = peek();
		elementData.remove(len - 1);
		return o;
	}
	
	public Object peek(){
		int len = elementData.size();
		
		if(len == 0)
			throw new EmptyStackException();
		
		return elementData.get(len - 1);
	}
	
	public boolean isEmpty(){
		int len = elementData.size();
		
		return len == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
