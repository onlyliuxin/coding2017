package com.coding.basic.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private Object[] data = new Object[10];
	private int size;
    private int top1, top2;
    
    public TwoStackInOneArray(int n){
    	data = new Object[n];
    	size = n;
    	top1 = -1;
        top2 = data.length;
    }
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		ensureCapacity();
		data[++top1] = o;
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		ensureCapacity();
		data[--top2] = o;
	}
	public void ensureCapacity(){
		if(top2-top1>1){
			return;
		} else{
			
			Object[] newArray = new Object[data.length*2];
			System.arraycopy(data, 0, newArray, 0, top1+1);
			
			int stack2Size = data.length-top2;
			int newTop2 = newArray.length-stack2Size;
			System.arraycopy(data, top2, newArray, newTop2, stack2Size);
			
			top2 = newTop2;
			data = newArray;
		}
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(top1 == -1){
			throw new RuntimeException("Stack1 is empty");
		}
		Object o = data[top1];
		data[top1] = null;
		top1--;
		return o;
		
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(top2 == data.length){
			throw new RuntimeException("Stack2 is empty");
		}
		Object o = data[top2];
		data[top2] = null;
		top2++;
		return o;
	}
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(top1 == -1){
			throw new RuntimeException("Stack1 is empty");
		}
		return data[top1];
	}
	
	
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if(top2 == data.length){
			throw new RuntimeException("Stack2 is empty");
		}
		return data[top2];
	}
	
	public Object[] stack1ToArray(){
		return Arrays.copyOf(data, top1+1);
	}
	public Object[] stack2ToArray(){
		int size = data.length-top2;
		Object [] stack2Data = new Object[size];
		int j=0;
		for(int i=data.length-1; i>=top2 ;i--){
			stack2Data[j++] = data[i];
		}
		return stack2Data;	
	}
}
