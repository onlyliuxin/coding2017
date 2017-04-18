package com.coding.basic;

import java.util.Arrays;

public class Tqueue {
	private Object[] elementData = new Object[100];
    private int head;//队头指针
    private int foot;//队尾指针
    
    Tqueue(){
    	head=foot=0;
    }
    
	public void enQueue(Object o){
		ensureCapacity(size()+1);
		elementData[foot++]=o;
	}
	
	public Object deQueue(){
		return elementData[head];
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return foot-head;
	}
	
	private void ensureCapacity(int minCapacity) {
		int oldCapacity = elementData.length;
		if(minCapacity>oldCapacity){
			int newCapacity = (minCapacity *3)/2+1;
			if(newCapacity<minCapacity)
				newCapacity=minCapacity;
			Arrays.copyOf(elementData, newCapacity);
		}
	}
}
