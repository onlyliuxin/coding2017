package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		if(!isEmpty()){
			return elementData.remove(size()-1);
		}else{
			return null;
		}
	}
	
	public Object peek(){
		if(!isEmpty()){
			return elementData.get(size()-1);
		}else{
			return null;
		}
	}
	public boolean isEmpty(){
		if(size() > 0){
			return false;
		}else{
			return true;
		}
	}
	public int size(){
		return elementData.size();
	}
}
