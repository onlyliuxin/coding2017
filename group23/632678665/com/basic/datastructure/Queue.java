package com.basic.datastructure;
import java.util.LinkedList;
public class Queue {
	private LinkedList linkedList=new LinkedList();
	
	public void enQueue(Object o){
		linkedList.add(o);
	}
	
	public Object deQueue(){
		Object o=linkedList.get(linkedList.size()-1);
		linkedList.remove(linkedList.size()-1);
		return o;
	}
	
	public boolean isEmpty(){
		return linkedList.isEmpty();
	}
	
	public int size(){
		return linkedList.size();
	}
}
