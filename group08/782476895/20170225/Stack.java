package com.sl.test20170221;

public class Stack {
	private List elementData = new ArrayList();
	
	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		int size = elementData.size();
		Object obj = elementData.remove(size--);
		return obj;
	}
	
	public Object peek(){
		int size = elementData.size();
		return elementData.get(size - 1);
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
}
