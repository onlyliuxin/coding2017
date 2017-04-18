package com.coding.basic.stack;

import com.coding.basic.ArrayList;
import com.coding.basic.List;

public class Stack<T> {
	private List elementData = new ArrayList();
	private int size = 0;
	public void push(Object o){		
		elementData.add(o);
		size ++;
	}
	
	public Object pop(){
		if (size == 0)
			return null;
		return elementData.remove(--size);
	}
	
	public Object peek(){
		if (size == 0)
			return null;
		return elementData.get(size - 1);
	}
	public boolean isEmpty(){
		return size == 0;
	}
	public int size(){
		return size;
	}
	public String toString() {
		StringBuffer join = new StringBuffer("[");
		for(int i = 0; i < size; i++) {
			join.append(elementData.get(i));
			if (i != size - 1) {
				join.append(",");
			} 
		}
		join.append("]");
		return join.toString();
	}
}
