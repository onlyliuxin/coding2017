package com.coding.basic;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o) throws Exception{
		this.elementData.add(0, o);
	}
	
	public Object pop() throws Exception{
		
		return elementData.remove(0);
	}
	
	public Object peek() throws Exception{
		return elementData.get(0);
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
