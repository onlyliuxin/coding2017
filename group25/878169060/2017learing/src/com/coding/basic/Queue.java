package com.coding.basic;

import java.util.NoSuchElementException;

public class Queue {
	private LinkedList linkedList=new LinkedList();
	//添加一个对象
	public void enQueue(Object o){	
		linkedList.add(o);
	}
	//删除一个对象
	public Object deQueue(){
		//如果Queue的size小于1，则返回数组越界异常
		if(linkedList.size()<1){
			throw new NoSuchElementException();
		}
		return null;
	}
	//判断Queue是否为空
	public boolean isEmpty(){
		return linkedList.size()==0;
	}
	//返回queue长度
	public int size(){
		return linkedList.size();
	}
}
