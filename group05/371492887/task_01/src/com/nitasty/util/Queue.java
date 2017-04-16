package com.nitasty.util;

import java.util.EmptyStackException;

public class Queue {
	
	LinkedList elementData=new LinkedList();
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		int len=elementData.size();
		if(len==0)
			throw new EmptyStackException();// TODO 确定对应Exception对象
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
}
