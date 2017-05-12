package com.coding.basic.stack;

import java.util.NoSuchElementException;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	
	private static final int GROW_UP_SIZE = 100;
	private int index1 =0;
	private int index2 = data.length-1;
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(o!=null){
			checkIsMeeting(index1);
			data[index1++] = o;
		}else{
			throw new NullPointerException();
		}
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(index1==0)
			throw new NoSuchElementException();
		Object first = data[index1-1];
		data[index1--] = null;
		return first;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(index1==0)
			throw new NoSuchElementException();
		return data[index1-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(o!=null){
			checkIsMeeting(index2);
			data[index2--] = o;
		}else{
			throw new NoSuchElementException();
		}
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(index2==data.length-1)
			throw new NoSuchElementException();
		Object last = data[index2+1];
		data[index2++] = null;
		return last;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if(index2==data.length-1)
			throw new NoSuchElementException();
		return data[index2+1];
	}
	
	public int getSize(){
		System.out.println(index1);
		System.out.println(index2);
		return (index1-0)+(data.length-1-index2);
	}
	
	/**
	 * 检查是否碰头
	 */
	private void checkIsMeeting(int index){
		if(data[index]!=null){
			growUp();
		}
	}
	
	/**
	 * 检查数组长度是否越界,越界就自动增长100
	 */
	private void growUp() {
		Object[] elementGrow = new Object[data.length + GROW_UP_SIZE];
		int start = 0;
		int end = elementGrow.length-1;
		if(index1!=0)
		while(data[index1-1]!=null){
			elementGrow[start++] = pop1();
		}
		if(index2!=data.length-1)
		while(data[index2+1]!=null){
			elementGrow[end--] = pop2();
		}
		data = elementGrow;
		index1 = start;
		index2 = end;
	}
	
}
