package com.coding.basic.stack;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData;
	private int elementCount;
	
	public Stack() {
		this.elementData = new ArrayList();
		this.elementCount = 0;
	}
	public void push(Object o){
		elementData.add(o);
		elementCount++;
	}
	
	public Object pop(){
		Object object = elementData.remove(elementCount-1);
		elementCount--;
		return object;
	}
	
	public Object peek(){
		if(isEmpty()){
			 throw new EmptyStackException();
		}
		return elementData.get(elementCount-1);
	}
	public boolean isEmpty(){
		return elementCount==0;
	}
	public int size(){
		return elementCount;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < elementData.size(); i++) {
			sb.append(elementData.get(i)).append(",");
		}
		sb = sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}