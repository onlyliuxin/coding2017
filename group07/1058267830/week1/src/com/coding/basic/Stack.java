package com.coding.basic;

public class Stack {

	private ArrayList list;
	public Stack(){
		list = new ArrayList(1);
	}
	
	public void push(Object o){
		list.add(o);
	}
	
	public Object pop(){
		return list.remove(0);
	}
	
	public Object peek(){
		return list.get(0);
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
