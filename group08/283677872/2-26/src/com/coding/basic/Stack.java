package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop() throws Exception{
		if(isEmpty())
		{
			throw new Exception("Stack pop error");
		}
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek() throws Exception{
		if(isEmpty())
		{
			throw new Exception("Stack pop error");
		}
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()>0;
	}
	public int size(){
		return elementData.size();
	}
}
