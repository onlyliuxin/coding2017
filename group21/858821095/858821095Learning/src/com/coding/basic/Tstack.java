package com.coding.basic;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Tstack {
	private Object[] elementData = new Object[100];
	private int size;
	
	public void push(Object o){	
		ensureCapacity(size+1);
		elementData[size++]=o;
	}
	public void clear(){	
		for(int i=0;i<size;i++){
			elementData[i]=null;
		}
		size=0;
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

	public Object pop(){
		Object ele = peek();
		elementData[size--]=null;
		return ele;
		
	}
	
	public Object peek(){
		if(size==0)
			throw new EmptyStackException();
		return elementData[size-1];
	}
	public boolean isEmpty(){
		return this.size()==0;
	}
	public int size(){
		return this.size;
	}
	public int search(Object o){
		if(o==null){
			for(int i=0;i<size;i++){
				if(elementData[i]==null)
					return i;
			}
		}else{
			for(int i=0;i<size;i++){
				if(o.equals(elementData[i]))
					return i;
			}
		}
		return -1;
	}
}
