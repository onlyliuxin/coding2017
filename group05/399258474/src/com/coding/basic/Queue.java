package com.coding.basic;

public class Queue {
	private LinkedList list = new LinkedList();
	
	public void enQueue(Object o){	
		list.add(o);
	}
	
	public Object deQueue(){
		if(isEmpty()){
			throw new RuntimeException("已为空");
		}
		Object o = list.get(0);
		list.remove(0);
		return o;
	}
	
	public boolean isEmpty(){
		if(size() == 0){
			return true;
		}else{
			return false;
		}
	}
	
	public int size(){
		return list.size();
	}
	
	@Override
	public String toString() {
		return list.toString();
	}
}
