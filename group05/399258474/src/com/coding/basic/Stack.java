package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		if(isEmpty()){
			throw new RuntimeException("已为空");
		}
		Object o = elementData.get(elementData.size()-1);
		elementData.remove(elementData.size()-1);
		return o;
	}
	
	public Object peek(){
		Object o = elementData.get(elementData.size()-1);
		return o;
	}
	public boolean isEmpty(){
		int size = elementData.size();
		if(size == 0){
			return true;
		}else{
			return false;
		}
	}
	public int size(){
		return elementData.size();
	}
	
	@Override
	public String toString() {
		return elementData.toString();
	}
}
