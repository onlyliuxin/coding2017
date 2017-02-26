package com.easy.util.myqueue;

import com.easy.util.mylinkedlist.LinkedList;

public class Queue {
	
	LinkedList linkedList;
	public Queue(){
		linkedList=new LinkedList();
	}
	
	public void enQueue(Object o){	
		linkedList.addLast(o);
	}
	
	public Object deQueue(){
		return linkedList.removeFirst();
	}
	
	public boolean isEmpty(){
		return size()==0;
	}
	
	public int size(){
		return linkedList.size();
	}
	
	@Override
	public String toString() {
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<linkedList.size();i++){
			sb.append(linkedList.get(i)+",");
		}
		String msg=sb.toString();
		return "["+msg.subSequence(0, msg.length()-1)+"]";
	}
}
