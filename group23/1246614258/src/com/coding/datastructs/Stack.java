package com.coding.datastructs;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		if (elementData.size()<=0) {
			throw new NullPointerException("Stack里面无数据，不能进行删除操作"); 
		}
		Object data = elementData.get(elementData.size()-1);
		elementData.remove(size()-1);
		return data;
	}
	
	public Object peek(){
		Object data = elementData.get(elementData.size()-1);
		return data;
	}
	public boolean isEmpty(){
		if(elementData.size()>0){
			return false;
		}else{
			return false;	
		}
	}
	public int size(){
		return elementData.size();
	}
}
