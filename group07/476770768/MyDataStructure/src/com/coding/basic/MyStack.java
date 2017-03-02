package com.coding.basic;

import java.util.EmptyStackException;

public class MyStack {
	private MyArrayList elementData = new MyArrayList();
	int top = -1;//always points to top element
	
	public void push(Object o){
		elementData.add(o);
		top++;
	}
	
	public Object pop(){
		if(isEmpty()){
			throw new EmptyStackException();
		}else{
			Object tmp = elementData.remove(top);
			top--;
			return tmp;
		}
	}
	
	public Object peek(){
		if(isEmpty()){
			throw new EmptyStackException();
		}else{
			Object tmp = elementData.get(top);
			return tmp;
		}
	}
	
	public boolean isEmpty(){
		return top < 0;
	}
	
	public int size(){
		return top + 1;
	}
	
	public MyIterator iterator() {
		return elementData.iterator();
	}
}
