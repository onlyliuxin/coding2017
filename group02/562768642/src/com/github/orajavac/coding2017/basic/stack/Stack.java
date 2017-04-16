package com.github.orajavac.coding2017.basic.stack;

import com.github.orajavac.coding2017.basic.linklist.LRUPageFrame;

public class Stack {
	
	private LRUPageFrame l;
	
	public Stack(int capacity){
		l = new LRUPageFrame(capacity);
	}
	
	public void push(Object o){
		l.addFirst(o);
	}
	
	public String toString(){
		return l.lastToString();
	}
	
	public Object[] getElements(int len){
		return l.getElements(len);
	}
	
	public void remove(Object obj){
		l.remove(obj, null);
	}
	
	public Object getFirstNode(){
		return l.getFirstNode();
	}
}
