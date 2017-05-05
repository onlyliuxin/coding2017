package com.coding.basic;

public class Queue {
	private ArrayList elementData = new ArrayList();
	public void enQueue(Object o) throws Exception{
		elementData.add(0, o);
	}
	
	public Object deQueue() throws Exception{
		if(isEmpty()){
			return null;
		}
		return elementData.remove(elementData.size()-1);
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
