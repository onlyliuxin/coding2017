package com.coding.basic;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();
	int size = elementData.size();

	public void push(Object o){
		elementData.add(o);
		size++;
	}

	public Object pop(){
		if(this.isEmpty()==true)
		{
			throw new EmptyStackException();
		}
		ArrayList popData = (ArrayList) elementData.remove(elementData.size()-1);
		size--;

		return popData;

	}

	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return size;
	}
}
