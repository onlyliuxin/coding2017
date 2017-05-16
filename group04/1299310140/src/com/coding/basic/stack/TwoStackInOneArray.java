package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	
	private Object[] data = new Object[10];
	private int pos1 = 0;//指向栈1的下一个空位
	private int pos2 = data.length - 1;//指向栈2的下一个空位
	
	public Boolean isEmpty1(){
		return pos1 == 0;
	}
	
	public Boolean isEmpty2(){
		return pos2 == data.length - 1;
	}
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		this.data[pos1] = o;
		pos1++;
		if(pos1 == pos2){//扩容
			grow();
		}
	}
	
	private void grow(){//容量增加一倍
		Object[] target = new Object[this.data.length * 2];
		System.arraycopy(this.data, 0, target, 0, pos1);
		System.arraycopy(this.data, pos2 + 1, target, pos2 + 1 + data.length, data.length - pos2 -1);
		pos2 = pos2 + data.length;
		data = target;
	}
	
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(this.isEmpty1()){
			throw new RuntimeException("The stack1 is empty!!!");
		}
		
		pos1--;
		Object result = this.data[pos1];
		this.data[pos1] = null;
		return result;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	public Object peek1(){
		return this.data[pos1-1];
	}
	
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		this.data[pos2] = o;
		pos2--;
		if(pos1 == pos2){//扩容
			grow();
		}
	}
	
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(isEmpty2()){
			throw new RuntimeException("The stack2 is empty!!!");
		}
		
		pos2++;
		Object result = this.data[pos2];
		this.data[pos2] = null;
		return result;
	}
	
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	public Object peek2(){
		return this.data[pos2 + 1];
	}
	
}
