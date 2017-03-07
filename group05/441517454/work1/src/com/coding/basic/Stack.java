package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		if(elementData.size()>0)
			
			{ int size =elementData.size();
			elementData.remove(size-1);
		return elementData.remove(size-1);}
		else return null;
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		if(elementData.size()>0)
		return false;
		else return true;
	}
	public int size(){
		return elementData.size();
	}
}
