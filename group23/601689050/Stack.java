package com.bjsxd.test;

public class Stack {
	private int top = -1;
	private Object[] elements;
	private int size = 0;
	public Stack(){
		elements = new Object[100];
	}
	public void push (Object o){
		elements[this.size] = o;
		this.size++;
	}
	public Object pop(){
		if (this.size != 0){
			Object temp = elements[size-1];
			elements[size-1]=0;
			size--;
			return temp;
		}else{
			System.out.println("Õ»¿Õ");
			return 0;
		}
	}
	public Object peek(){
		if(!this.isEmpty()){
			Object temp = elements[this.size-1];
			elements[this.size-1] = 0;
			this.size--;
			return temp;
		}else{
			System.out.println("Õ»¿Õ");
			return 0;
		}
	}
	public boolean isEmpty(){
		return this.size == 0;
	}
}
