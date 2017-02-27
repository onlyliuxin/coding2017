package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		this.elementData.add(o);
	}
	
	public Object pop(){
		return this.elementData.remove(this.elementData.size()-1);
	}
	
	public Object peek(){
		return this.elementData.get(this.elementData.size()-1);
	}
	
	public boolean isEmpty(){
		if(this.elementData.size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		return this.elementData.size();
	}
	
	public String toString(){
		return this.elementData.toString();
	}
	
}
