package com.coding.basic.stack;

import java.util.EmptyStackException;

import com.coding.basic.ArrayList;

public class Stack<E> {
	private ArrayList<E> elementData = new ArrayList<E>();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public E pop(){
		int length = size();
		E lastData = peek();
		elementData.remove(length - 1);
		
		return lastData;
	}
	
	public E peek(){
		if(size()<=0)
			throw new EmptyStackException();
		
		return elementData.get(size()-1);
	}
	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return elementData.size();
	}
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int i = 0; i < size(); i++) {
			sb.append(elementData.get(i)).append(", ");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.deleteCharAt(sb.length()-1);
		sb.append("]");
		return sb.toString();
	}
}
