package com.easy.util.mystack;

import com.easy.util.mylinkedlist.LinkedList;

public class Stack {
	private LinkedList elementData;
	
	public Stack(){
		elementData=new LinkedList();
	}
	
	public void push(Object o){		
		elementData.addLast(o);
	}
	
	public Object pop(){
		return elementData.removeLast();
	}
	
	public Object peek(){
		return elementData.get(size()-1);
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<elementData.size();i++){
			sb.append(elementData.get(i)+",");
		}
		String msg=sb.toString();
		return "["+msg.subSequence(0, msg.length()-1)+"]";
	}
}
