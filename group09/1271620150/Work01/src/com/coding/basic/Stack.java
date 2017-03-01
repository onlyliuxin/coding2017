package com.coding.basic;

public class Stack {
	private static final int CAPACITY = 10;
	private  int capacity;
	private  int top = -1;
	Object[] array;	
	public Stack(){
		this.capacity = CAPACITY;
        array = new Object[capacity];
	}
	public void push(Object o) throws Exception{		
		if(size()== CAPACITY){
            throw new Exception("Stack is full");
        }
        array[++ top] = o;
	}
	
	public Object pop() throws Exception{
		  if(isEmpty()){
	            throw new Exception("Stack is empty");
	        }
	        return array[top --];
	}
	
	public Object peek() throws Exception{
		 if(isEmpty()){
	            throw new Exception("Stack is empty");
	        }
	        return array[top];
	}
	
	public boolean isEmpty(){
		return (top < 0);
	}
	
	public int size(){
		if (isEmpty())
			return 0;
		else
		return top + 1;
		
	}
	
}