package com.coding.basic;

public class Stack {
	private ArrayList al = null;
	
	public Stack(){
		al = new ArrayList();
	}
	
	public void push(Object o){
		al.add(o);
	}
	
	public Object pop(){
		return al.remove(al.size()-1);
	}
	
	public Object peek(){
		return (al.size() == 0) ? null : al.get(al.size() -1);
	}
	
	public boolean isEmpty(){
		return (al.size() == 0) ? true : false;
	}
	
	public int size(){
		return al.size();
	}
}
