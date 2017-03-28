package com.homework01;

public class Stack {

	private ArrayList array = new ArrayList();
	
	public void push(Object o){
		array.add(o);
	}
	public Object pop(){
		return array.remove(array.size()-1);
	}
	
	public boolean isEmpty(){
		if(array.size()<=0)
			return true;
		return false;
	}
	
	public int size(){
		return array.size();
	}
	public static void main(String[] args) {
		Stack sc = new Stack();
		sc.push("hello world");
		sc.push("java");
		sc.push("jvm");
	}
	
}
