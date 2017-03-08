package com.zzk.coding2017.zuoye_1;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	public Object pop(){
		if(elementData.size()==0){
			return null;
		}
		else{
			Object result =  elementData.get(elementData.size()-1);
			elementData.remove(elementData.size()-1);
			return result;
		}
	}
	
	public Object peek(){
		if(elementData.size()==0){
			return null;
		}
		else{
			return elementData.get(elementData.size()-1);
		}
	}
	public boolean isEmpty(){
		if(elementData.size()==0)
			return true;
		else
			return false;
	}
	public int size(){
		return elementData.size();
	}
}
