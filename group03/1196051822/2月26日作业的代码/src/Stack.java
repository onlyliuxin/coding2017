package com.byhieg.coding2017;

import java.util.EmptyStackException;

public class Stack {
	private ArrayList elementData = new ArrayList();


	
	public void push(Object o){
        elementData.add(o);
	}
	
	public Object pop(){
        if (size() == 0) {
            throw new EmptyStackException();
        }
        Object value = elementData.get(size() - 1);
        elementData.remove(size() - 1);
        return value;
    }
	
	public Object peek(){
        if (size() == 0) {
            throw new EmptyStackException();
        }
		return elementData.get(size() - 1);
	}

	public boolean isEmpty(){
		return size() == 0;
	}
	public int size(){
		return elementData.size();
	}
}
