package com.github.congcongcong250.coding2017.basic;

public class Stack {
	private LinkedList elementData = new LinkedList();
	
	public Stack(){
		elementData = new LinkedList();
	}
	
	public void push(Object o){	
		elementData.addLast(o);
	}
	
	public Object pop(){
		Object ret = elementData.removeLast();
		return ret;
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return (elementData.size() == 0);
	}
	public int size(){
		return elementData.size();
	}
	
	public void clear(){
		elementData.clear();
	}
}
