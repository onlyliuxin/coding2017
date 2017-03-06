package com.coding.basic;

public class Stack {

   private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		int index = elementData.size() - 1;
		Object o = elementData.remove(index);
		return o;
	}
	
	public Object peek(){
		int e = elementData.size() - 1;
		return elementData.get(e);
	}
	
	public boolean isEmpty(){
		if (elementData.size() == 0 )
			return true;
		else
			return false;
	}
	
	public int size(){
		return elementData.size();
	} 

}
