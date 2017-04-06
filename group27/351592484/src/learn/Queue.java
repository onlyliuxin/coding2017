package com.liam.learn.code2017;

public class Queue {

	private LinkedList linkedList;

	public Queue() {
		this.linkedList = new LinkedList();
	}

	public void enQueue(Object o){
		linkedList.add(o);
	}
	
	public Object deQueue(){
		if (linkedList == null || isEmpty()){
			return null;
		}
		return linkedList.removeFirst();
	}
	
	public boolean isEmpty(){
		return linkedList.size()==0;
	}
	
	public int size(){
		if (linkedList == null || isEmpty()){
			return 0;
		}
		return linkedList.size();
	}
}
