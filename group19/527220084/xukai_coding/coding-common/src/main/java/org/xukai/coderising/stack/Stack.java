package org.xukai.coderising.stack;

import org.xukai.common.ArrayList;

import java.util.EmptyStackException;

public class Stack {

	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		if (isEmpty()) {
			throw new EmptyStackException();
		}
		return elementData.get(elementData.size()-1);
	}

	public boolean isEmpty(){
		return elementData.size() == 0;
	}

	public int size(){
		return elementData.size();
	}

	public void display() {
		elementData.display();
	}
}
