package com.github.congcongcong250.coding2017.basic;

public class WStack {
	private WLinkedList elementData = new WLinkedList();
	
	public WStack(){
		elementData = new WLinkedList();
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
