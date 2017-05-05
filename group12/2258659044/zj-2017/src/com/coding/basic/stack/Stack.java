package com.coding.basic.stack;

import com.coding.basic.array.ArrayList;

public class Stack<E> {
	
	private ArrayList<E> elementData = new ArrayList<E>();
	
	public void push(E o){	
		
		elementData.add(o);
	}
	
	public E pop(){
		
		return elementData.remove(size()-1);
	}
	
	public E peek(){
		
		return elementData.get(size()-1);
	}
	public boolean isEmpty(){

		return size()==0;
	}
	public int size(){
		return elementData.size();
	}
	
	@Override
	public String toString() {
		
		StringBuffer sBuffer = new StringBuffer();
		sBuffer.append("[");	
		
		for (int i = size()-1; i >= 0; i--) {
			if(i == 0){
				sBuffer.append(elementData.get(i)+"]");
			}else{
				sBuffer.append(elementData.get(i)+",");
			}			
		}
		return sBuffer.toString();
	}
}
