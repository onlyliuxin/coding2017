package com.coding.basic.stack;
import java.util.Arrays;
import java.util.EmptyStackException;


import com.coding.basic.array.ArrayList;

public class Stack {
	private ArrayList elementData = new ArrayList();
	private int topIndex =-1;//栈顶元素索引
	private static final int DEFAULT_MAX_SIZE = 50;
	private int length;
	
	//压入一个元素
	public void push(Object o){
		topIndex++;
		elementData.add(o);
		
		
		
	}
	
	public Object pop(){
		if(elementData.size() ==0 ){
			throw new EmptyStackException();
		}
		topIndex--;
		return elementData.remove(elementData.size()-1);
		
	}
	
	public Object peek(){
		if(elementData.size()!=1){
			return elementData.get(elementData.size()-1);
		}
		return null;
	}
	public boolean isEmpty(){
		return topIndex>0;
	}
	public int size(){
		return topIndex;
	}
}
