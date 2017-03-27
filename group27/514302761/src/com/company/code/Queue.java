package com.company.code;

public class Queue {
	private LinkedList elementDatas=new LinkedList();
	public void enQueue(Object o){
        elementDatas.add(o);
	}
	
	public Object deQueue(){

	    return elementDatas.removeFirst();
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return elementDatas.size();
	}
}
