package com.coding.basic.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	int N = 5;
	Object[] data = new Object[N];
	int index1 = 0;
	int index2 = N-1;
	
	int size1 = 0;
	int size2 = 0;
	
	private boolean isFull(){
		if(index1 > index2){
			return true;
		}
		return false;
	}
	
	private void expend(){
		int newSize = data.length * 2;
		Object[] newData = new Object[newSize];
		int length1 = index1 ;
		int length2 = data.length - index2 - 1;
		System.arraycopy(data, 0, newData, 0, length1);
		System.arraycopy(data, index2+1, newData, newSize - length2, length2);
		data = newData;
		index2 = data.length - length2;
		
	}
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(isFull()){
			expend(); 
		}
		data[index1 ++ ] = o;
		size1 ++;
	}
	
	private boolean isStack1Empty(){
		if(index1 == 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(! isStack1Empty())
		{
			Object item = data[index1 - 1];
			data[index1 - 1] = null;
			index1 --;
			return item;
		}
		return null;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(! isStack1Empty()){
			return data[index1 -1];
		}
		return null;
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(isFull()){
			expend();
		}
		data[index2] = o;
		index2 --;
		size2++;

	}
	private boolean isStack2Empty(){
		if(index2 >= data.length -1){
			return true;
		}
		return false;
	}
	
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if( ! isStack2Empty()){
			Object item =  data[index2 + 1];
			data[index2 + 1] = null;
			index2 ++;
			size2 --;
			return item;
		}
		return null;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if( ! isStack2Empty()){
			Object item = data[index2 + 1];
			return item;
		}
		return null;
	}
	
	public void dump(){
		System.out.println("index 1: " + index1 + " size1: " + size1);
		System.out.println("index 2: " + index2 + " size2: " + size2); 
		System.out.println(Arrays.toString(data));
	}
	
}