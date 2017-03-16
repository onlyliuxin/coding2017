package com.coding.basic;

public class Stack {
	private Object[] data;
	private int capacity;
	private int size;
	public Stack(){
		capacity = 16;
		size = 0;
		data = new Object[capacity];
	}
	public void push(Object o){	
		if(size < capacity){
			data[size++]  = o;
		}else {
			ensureCapacity();
			data[size++] = o;
		}
	}
	
	private void ensureCapacity() {
		capacity = capacity*2;
	}
	public Object pop(){
		if(size > 0){
			System.out.println(data[size-1]);
			//data[size--] = null;
		}else {
			System.out.println("Empty stack");
		}
		size--;
		return data[size];
	}
	
	public Object peek(){
		if(size>0){
			return data[size-1];
		}else {
			return null;
		}
	}
	public boolean isEmpty(){
		
		return size==0;
	}
	public int size(){
		return size;
	}
}
