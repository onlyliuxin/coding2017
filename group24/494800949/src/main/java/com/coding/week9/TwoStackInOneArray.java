package com.coding.week9;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	private int pos1 = 0;
	private int pos2 = data.length - 1;
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if (o == null) {
			throw new RuntimeException("data can not be null");
		}
		data[pos1++] = o;
		if (pos1 == pos2) {
			doubleCapacity();
		}
	}

	private void doubleCapacity(){
		int oldCapacity = data.length;
		int newCapacity = oldCapacity << 1;
		if (newCapacity < 0) {
			throw new RuntimeException("capacity is too large");
		}
		Object[] dataNew = new Object[newCapacity];
		System.arraycopy(data, 0, dataNew, 0, pos1);
		int moveNum = oldCapacity - pos2;
		int newPos2 = newCapacity - moveNum;
		System.arraycopy(data, pos2, dataNew, newPos2, moveNum);
		pos2 = newPos2;
		data = dataNew;
	}

	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if (pos1 == 0) {
			throw new RuntimeException("stack1 is empty");
		}
		Object e = data[--pos1];
		data[pos1] = null;
		return e;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if (pos1 == 0) {
			return null;
		}
		return data[pos1 - 1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if (o == null) {
			throw new RuntimeException("data can not be null");
		}
		data[pos2--] = o;
		if (pos1 == pos2) {
			doubleCapacity();
		}
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if (pos2 == data.length - 1) {
			throw new RuntimeException("stack2 is empty");
		}
		Object e = data[++pos2];
		data[pos2] = null;
		return e;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if (pos2 == data.length - 1) {
			return null;
		}
		return data[pos2 + 1];
	}
	
}
