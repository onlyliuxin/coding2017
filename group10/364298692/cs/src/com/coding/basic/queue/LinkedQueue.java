package com.coding.basic.queue;

import com.coding.basic.Queue;
import com.coding.basic.linkedList.LinkedList;

public class LinkedQueue implements Queue{
	
	private LinkedList list;
	private int size;

	public LinkedQueue(){
		list = new LinkedList();
		size = list.size();
	}
	
	
	@Override
	public void enQueue(Object o) {
		list.add(size, o);		
	}

	@Override
	public Object deQueue() {
		if(size > 0){
			Object o = list.remove(0);
			size = list.size();
			return o;
		}else{
			throw new IndexOutOfBoundsException();
		}		
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		return size;
	}

}
