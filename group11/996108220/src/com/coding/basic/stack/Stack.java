package com.coding.basic.stack;

import com.coding.basic.ArrayList;

public class Stack {
	//用动态数组实现栈
	private ArrayList elementData = new ArrayList();
	
	public void push(Object o){	
		elementData.add(o);
	}
	
	public Object pop(){
		return elementData.remove(elementData.size()-1);
	}
	
	public Object peek(){
		return elementData.get(elementData.size()-1);
	}
	public boolean isEmpty(){
		return elementData.size()==0?true:false;
	}
	public int size(){
		return elementData.size();
	}
	public String toString() {

		StringBuffer buffer=new StringBuffer("[");
		for (int i = elementData.size()-1; i >=0; i--) {
			if (i==0) {
				buffer.append(elementData.get(i).toString()+"]");
			}
			else {
				buffer.append(elementData.get(i).toString()+",");
			}
		}
		return buffer.toString();
	}

}
