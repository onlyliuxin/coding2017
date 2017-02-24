package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();

//	public Stack()
//	{
//		elementData
//	}

	public void push(Object o){
		elementData.add(o);
	}
	
	public Object pop(){
		Object o = elementData.remove(elementData.size());
		return o;
	}
	
	public Object peek(){
		Object o = elementData.get(elementData.size());
		return o;
	}
	public boolean isEmpty(){
		if (elementData.size() == 0)
		{
			return true;
		}
		return false;
	}
	public int size(){
		return elementData.size();
	}
}
