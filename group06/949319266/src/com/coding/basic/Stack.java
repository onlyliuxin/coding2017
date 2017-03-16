package com.coding.basic;

import java.util.ArrayList;

public class Stack {
	private ArrayList element;	
	int top;
	public Stack() {
		element = new ArrayList();
		top = 0;
	}
	
	public void push(Object o){	
		element.add(o);
		top++;
	}
	
	public void pop(){
		if(isEmpty()) {
			System.out.println("Õ»ÊÇ¿ÕµÄ");
			System.exit(0);
		}
		element.remove(top-1);
		top--;
	}
	
	public Object peek(){
		return element.get(top-1);
	}
	public boolean isEmpty(){
		return top == 0?true:false;
	}
	public int size(){
		return top;
	}
}
