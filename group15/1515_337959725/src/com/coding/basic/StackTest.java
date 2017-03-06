package com.coding.basic;

public class StackTest {
	private LinkedListTest list = new LinkedListTest();
	
	public void push(Object o){
		list.add(o);
	}
	
	public Object pop(){
		Object obj = list.removeLast();
		return obj;
	}
	
	public Object peek(){
		Object obj = list.get(0);
		return obj;
	}
	
	public boolean isEmpty(){
		int size = list.size();
		if(size==0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		int size = list.size();
		return size;
	}
}
