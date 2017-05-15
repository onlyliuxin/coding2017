package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	int top1=-1;
	int top2=10;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if (top1+1==top2) {
			grow(data,top1,top2);
		}
		data[++top1]=o;		
	}
	private void grow(Object[] data2,int top1, int top2) {
		// TODO Auto-generated method stub
		Object[] data=new Object[data2.length+data2.length/2];
		System.arraycopy(data2,0,data,0,top1+1);
		System.arraycopy(data2,top2,data,top2+data2.length/2,data2.length-top2+1);
		this.top2=top2+data2.length/2;
		this.data=data;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if (top1+1>0) {
			return data[top1--];
		}
		return null;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if (top1+1>0) {
			return data[top1];
		}
		return null;
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if (top1+1==top2) {
			grow(data,top1,top2);
		}
		data[--top2]=o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if (top2<data.length) {
			return data[top2++];
		}
		return null;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if (top2<data.length) {
			return data[top2];
		}
		return null;
	}
}
