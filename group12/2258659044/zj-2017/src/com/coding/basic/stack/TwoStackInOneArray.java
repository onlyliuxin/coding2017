package com.coding.basic.stack;

import java.util.EmptyStackException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @@author zj
 *
 */
public class TwoStackInOneArray {
	
	Object[] data = new Object[10];
	
	private int index1 = 0;
	private int index2=data.length-1;
	
    /*扩容因子*/
	private static final int FACTOR = 10;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		
		if(index1-1==index2){
			grow();
		}
		data[index1++] = o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		
		rangeCheck(index1);
		
		return data[--index1];
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		
		rangeCheck(index1);
		
		int popIndex = index1-1;
		
		return data[popIndex];
	}
	
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		
		if(index2+1==index1){
			grow();
		}
		data[index2--] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		
		rangeCheck(index2);
		
		return data[++index2];
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		
		rangeCheck(index2);
		
		int popIndex = index2+1;
		
		return data[popIndex];
	}
	
	/**
	 * 扩容
	 */
	private void grow(){
		
		//原来数组长度
		int origLen =  data.length;
		
		//扩容后现在的长度
		int curLen = origLen+FACTOR;
		
		//栈2中数据个数
		
		int num = origLen - index2-1;
		
		//新建临时数组
		Object[] temp = new Object[curLen];
		
		//将栈1的内容拷贝到新数组中
		System.arraycopy(data, 0, temp, 0, index1);
		
		//将栈2的内容拷贝到新数组中
		System.arraycopy(data, index1, temp,curLen-num , num);
		
		//更新index2与数组		
		this.index2 = curLen-num-1;
		this.data = temp;
	}
	
	private void rangeCheck(int index){
		
		if(index==0||index == data.length-1){
			throw new EmptyStackException();
		}
	}
	
}
