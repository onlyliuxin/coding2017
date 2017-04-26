package com.coding.basic.stack;

import com.coding.basic.ArrayList;


public class Stack {
	private ArrayList list = new ArrayList();
	
	public void push(Object o){		
		list.add(o);
	}
	
	public Object pop(){
		if(list.size() == 0)
			return null;
		Object result = list.get(0);
		list.remove(0);
		return result;
	}

	public Object peek(){
		if(list.size() == 0)
			return null;
		return list.get(0);
	}
	public boolean isEmpty(){
		return list.size() == 0;
	}
	public int size(){
		return list.size();
	}
}
