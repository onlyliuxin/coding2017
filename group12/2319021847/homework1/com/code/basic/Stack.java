package com.coding.basic;

 

public class Stack {
	private Object[] elements;
	private int size;
	
	
	public Stack()
	{
		this.size = 0;
		this.elements = new Object[10];
	}
	public void push(Object o){	
		if(o == null)
			return;
		size++;
		elements[size-1] = o;
	}
	
	public Object pop(){
		if(isEmpty())
		{
			return null;
		}
		Object pop = elements[size-1]; 
		size--;
		return pop;
	}
	
	public Object peek(){
		if(isEmpty())
		{
			return null;
		}
		return elements[size-1];
	}
	public boolean isEmpty(){
		return size < 1;
	}
	public int size(){
		return size;
	}
}