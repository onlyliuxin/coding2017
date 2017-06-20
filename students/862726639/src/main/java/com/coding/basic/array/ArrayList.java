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
		if (size < index) {//判断是否存在
			return null;
		}
		return elementData[index];
	}
	//返回删除对象
	public Object remove(int index){
		if (index > size) {//不存在
			System.out.println("数据不存在");
			return null;
		} else if (index == size) {//刚好最后一个
			return elementData[index];
		} else {
			
		}
		return null;
	}
	
	public int size(){
		if (elementData[0]==null) {
			return -1;
		}
		return elementData.length;
	}
	
	public Iterator iterator(){
		return null;
	}
	
}
