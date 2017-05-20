package com.github.orajavac.coding2017.basic.stack;

import com.github.orajavac.coding2017.basic.linklist.LRUPageFrame;

public class Stack {
	
	private LRUPageFrame l;
	
	public Stack(int capacity){
		l = new LRUPageFrame(capacity);
	}
	
	public Stack(){
		l = new LRUPageFrame();
	}
	
	//栈使用
	public void push(Object o){
		//l.addFirst(o);
		l.add(o);
	}
	
	//StackWithTwoQueues 使用
	public void push1(Object o){
		l.addFirst(o);
	}
	
	public Object pop(){
		return l.remove();
	}
	
	public Object popLow(){
		return l.removeLow();
	}
	
	public String toString(){
		return l.lastToString();
	}
	
	public String positiveToString(){
		return l.toString();
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
	
	public int length(){
		return l.getLength();
	}
}
