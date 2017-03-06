package com.coding.basic;

public class Stack {
	
	//
	private ArrayList elementData = new ArrayList();
	private int top = 0;
	
	public void push(Object o){
		elementData.add(o);
		top++;
	}
	
	public Object pop(){
		if(!isEmpty()){
			System.out.println("stack is empoty");
			return null;
		}
		Object obj = elementData.remove(top);
		top--;
		return obj;
	}
	
	public Object peek(){
		return elementData.get(top);
	}
	
	public boolean isEmpty(){
		if(top != 0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return top++;
	}
	
	

}
