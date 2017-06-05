package com.coding.basic.stack;

import com.coding.basic.array.ArrayList;
/**
 * 先进后出(FILO)
 * @author Administrator
 *
 * @param <T>
 */
public class Stack<T> {
	private ArrayList<T> elementData = new ArrayList<>();
		
	public void push(T o){
		elementData.add(elementData.size(), o);
	}
	
	public T pop(){
		T obj = elementData.remove(elementData.size()-1);
		return obj;
	}
	
	public T peek(){
		T obj = elementData.get(0);
		return obj;
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
	
	public String toString(){
		StringBuilder sb = new StringBuilder("[");
		int i;
		for(i=0; i<elementData.size()-1; i++){
			sb.append(elementData.get(i) + ",");
		}
		sb.append(elementData.get(i) + "]");
		return sb.toString();
	}
}
