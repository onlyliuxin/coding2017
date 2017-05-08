package com.github.ipk2015.coding2017.basic.stack;

import java.util.EmptyStackException;

import com.github.ipk2015.coding2017.basic.ArrayList;

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
	public String toString(){
		StringBuffer buffer=new StringBuffer();
		int size=elementData.size();
		for(int i=0;i<size;i++){
			buffer.append(elementData.get(i)+",");
		}
		return buffer.toString();
	}
}
