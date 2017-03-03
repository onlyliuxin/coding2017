package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){
		for (int i = 0; i < elementData.size(); i++) {
			if(elementData.get(i)==null){
				elementData.add(i, o);
			}
		}
	}
	
	
	public Object pop(){
		int length = elementData.size();
		elementData.remove(length-1);
		return elementData.get(length-1);
	}
	
	public Object peek(){
		int length = elementData.size();
		return elementData.get(length-1);
	}
	public boolean isEmpty(){
		if(elementData.size()==0){
			return true;
		}else{
			return false;
		}
	}
	public int size(){
		return elementData.size();
	}
}