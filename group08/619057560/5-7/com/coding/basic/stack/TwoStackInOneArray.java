package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	private Object[] data = new Object[10];
	
	private int size1 = 0, size2 = 0;
	
	private void ensureCapacity() {
		if (size1 + size2 >= data.length) {
			Object[] newData = new Object[data.length * 2 + 1];
			System.arraycopy(data, 0, newData, 0, size1);
			System.arraycopy(data, size1, newData, newData.length - size2 - 1, size2);
			data = newData;
		}
	}
	
	private void checkStack1() {
		if (size1 == 0) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkStack2() {
		if (size2 == 0) {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public int size1() {
		return size1;
	}
	
	public int size2() {
		return size2;
	}
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		ensureCapacity();
		data[size1++] = o;
	}
	
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		checkStack1();
		
		Object retObject = peek1();
		size1--;
		
		return retObject;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		checkStack1();
		return data[size1 - 1];
	}
	
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		ensureCapacity();
		data[data.length - 1 - size2++] = o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		Object retObject = peek2();
		size2--;
		
		return retObject;
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		checkStack2();
		
		return data[data.length - size2];
	}
	
}
