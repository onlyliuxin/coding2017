package com.github.congcongcong250.coding2017.basic;

public class Queue {
	private LinkedList elementData;
	
	public Queue(){
		elementData = new LinkedList();
	}
	
	public void enQueue(Object o){		
		elementData.addFirst(o);
	}
	
	public Object deQueue(){
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
