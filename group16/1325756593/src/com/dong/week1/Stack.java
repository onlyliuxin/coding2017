package com.dong.week1;

public class Stack {
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){		
		elementData.add(o);
	}
	
	
	public Object pop(){
		if(elementData.size()==0){
			throw new IndexOutOfBoundsException("Õ»Îª¿Õ");
		}
		return elementData.remove(elementData.size()-1);
		
	}
	
	public Object peek(){
		if(elementData.size()==0){
			throw new IndexOutOfBoundsException("Õ»Îª¿Õ");
		}
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	public int size(){
		return elementData.size();
	}
}
