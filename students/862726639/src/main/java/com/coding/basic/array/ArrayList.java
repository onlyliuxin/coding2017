package com.coding.basic.array;

import com.coding.basic.Iterator;
import com.coding.basic.List;
//实现arraylist写呢
public class ArrayList implements List {
	
	private int size = 0;
	
	private Object[] elementData = new Object[100];
	
	public void add(Object o){
		elementData[size++] = o;
	}
	public void add(int index, Object o){
		
	}
	
	public Object get(int index){
		return null;
	}
	
	public Object remove(int index){
		return null;
	}
	
	public int size(){
		return -1;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
