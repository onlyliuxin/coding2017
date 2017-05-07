package com.github.ipk2015.coding2017.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	private int pointer1 = 0;
	private int pointer2 = 9;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(pointer1 == pointer2){
			enlargeDataArray();
		}
		data[pointer1++] = o;
	}
	
	private void enlargeDataArray(){
		Object[] newData = new Object[data.length+100];
		for(int i = 0;i < pointer1;i++){
			newData[i] = data[i];
		}
		for(int i = pointer2+1;i < data.length;i++){
			newData[i+100] = data[i];
		}
		pointer2 += 100;
		data = newData;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		Object object = data[pointer1-1];
		data[pointer1-1] = null;
		pointer1--;
		return object;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[pointer1-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(pointer1 == pointer2){
			enlargeDataArray();
		}
		data[pointer2--] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		Object object = data[pointer2+1];
		data[pointer2+1] = null;
		pointer2++;
		return object;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		return data[pointer2+1];
	}
	
}
