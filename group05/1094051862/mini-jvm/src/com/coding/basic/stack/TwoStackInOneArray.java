package com.coding.basic.stack;

import java.util.Arrays;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	
	private int capacity = 10;
	Object[] data = new Object[capacity];
	
	private int DEFAULT_DILATATION = capacity/2;
	
	private int index1 = -1;
	private int index2 = capacity;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if(isFull()) {
			dilatation();
		}
		data[index1++] = o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if (stack1IsEmppty()) {
			throw new RuntimeException("stack1 is empty");
		}
		Object o = data[index1];
		data[index1--] = null;
		return o;
	}
	public boolean stack1IsEmppty() {
		return index1==-1;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if (stack1IsEmppty()) {
			throw new RuntimeException("stack1 is empty");
		}
		return data[index1];
	}
	/**
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if(isFull()) {
			dilatation();
		}
		data[--index2] = o;
		
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if (stack2IsEmpty()) {
			throw new RuntimeException("stack2 is empty");
		}
		Object o = data[index2];
		data[index2++] = null;
		return o;
	}
	public boolean stack2IsEmpty() {
		return index2==capacity;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if (stack2IsEmpty()) {
			throw new RuntimeException("stack2 is empty");
		}
		return data[index2];
	}
	
	public boolean isFull() {
		return (index1+1) == index2;
	}
	private void dilatation() {
		int newcapacity = DEFAULT_DILATATION + capacity;
		int newindex2 = newcapacity;
		Object[] temp = Arrays.copyOfRange(data, 0, index1);
		temp = Arrays.copyOf(temp, newcapacity);
		
		for (int i = capacity-1; i >= index2; i-- ) {
			temp[--newindex2] = data[i];
		}
		index2 = newindex2;
	}
}
