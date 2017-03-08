package com.coding.basic;


import java.util.EmptyStackException;

import javax.lang.model.element.QualifiedNameable;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		checkIsEmpty();
		return elementData.remove(size()-1);
	}

	private void checkIsEmpty() {
		if (isEmpty()){
			throw new EmptyStackException();
		}
	}
	
	public Object peek(){
		checkIsEmpty();
		return elementData.get(size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
	public static void main(String[] args) {
		Stack que = new Stack();
		que.push(10);
		que.push(11);
		que.push(12);
		System.out.println(que.peek());
		System.out.println(que.isEmpty());
		System.out.println(que.pop());
		System.out.println(que.pop());
		que.pop();
		System.out.println(que.isEmpty());
	}
}
