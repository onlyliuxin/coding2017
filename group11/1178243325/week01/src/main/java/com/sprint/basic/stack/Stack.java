package com.sprint.basic.stack;

import com.sprint.basic.exception.EmptyStackException;
import com.sprint.basic.list.ArrayList;
public class Stack {

	private ArrayList elementData;
	public Stack() {
		elementData = new ArrayList();
	}

	public boolean push(Object o){		
		elementData.add(o);
		return true;
	}
	
	public Object pop(){
		if (isEmpty()) {
			throw new EmptyStackException("栈空");
		}
		Object result = elementData.get(size()-1);
		elementData.remove(size()-1);
		return result;
	}
	
	public Object peek(){
		if (isEmpty()) {
			throw new EmptyStackException("栈空"); 
		}
		return elementData.get(0);
	}

	public boolean isEmpty(){
		return elementData.size() == 0;
	}

	public int size(){
		return elementData.size();
	}
}
