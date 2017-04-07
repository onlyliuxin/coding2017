package com.coding.basic;

public class Dequeue {
	private int size = 0;
	private LinkedList list = new LinkedList();
	
	public void enHeadQueue(Object o){	
		list.addFirst(o);
	}
	
	public Object deHeadQueue(){
		Object ret = list.removeFirst();
		return ret;
	}
	
	public void enLastQueue(Object o){	
		list.addLast(o);
	}
	
	public Object deLastQueue(){
		Object ret = list.removeLast();
		return ret;
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return list.size();
	}
}
