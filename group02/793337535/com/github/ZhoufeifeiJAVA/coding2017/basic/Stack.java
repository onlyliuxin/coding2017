package com.github.ZhoufeifeiJAVA.coding2017.basic;

public class Stack {
	private MyLinkedList llist = null;
	Stack(){
		llist = new MyLinkedList();
	}
	public void push(Object o){	
		llist.add(o);
	}
	
	public Object pop(){
		return llist.removeLast();
	}
	
	public Object peek(){
		return llist.get(llist.size()-1);
	}
	public boolean isEmpty(){
		if(llist.size() != 0)
			return true;
		else
			return false;
	}
	public int size(){
		return llist.size();
	}
}