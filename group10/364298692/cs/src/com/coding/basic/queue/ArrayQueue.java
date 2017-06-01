package com.coding.basic.queue;

import com.coding.basic.Queue;
import com.coding.basic.array.ArrayList;

public class ArrayQueue implements Queue{
	
	private ArrayList list;
	private int size;
	
	public ArrayQueue(){
		list = new ArrayList();
		size = list.size();
	}
	public ArrayQueue(int initialCapacity){
		list = new ArrayList(initialCapacity);
		size = list.size();
	}
	
	@Override
	public void enQueue(Object o){
		list.add(o);
		size = list.size();
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
