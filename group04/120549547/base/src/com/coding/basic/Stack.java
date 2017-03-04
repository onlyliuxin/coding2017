package com.coding.basic;
import com.coding.basic.LinkedList;

public class Stack {
	private LinkedList list;
	
	public Stack(){
		list = new LinkedList();
	}
	
	public void push(Object o){
		list.addLast(o);
	}
	
	public Object pop(){
		if(isEmpty()){
			System.out.println("栈空");
		}
		return list.removeLast();
		
	}
	
	public Object peek(){
		return list.get(list.size()-1);
	}
	public boolean isEmpty(){
		return list.isEmpty();
	}
	public int size(){
		return list.size();
	}
	
	@Override
	public String toString(){
		return list.toString();
	}
}
