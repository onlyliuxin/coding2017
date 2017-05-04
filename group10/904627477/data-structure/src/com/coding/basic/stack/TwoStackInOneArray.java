package com.coding.basic.stack;


/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	
	private final static int DEFALUT_SIZE = 5;
	
	Object[] data = new Object[DEFALUT_SIZE];
	
	int p1 = 0;
	int p2 = data.length-1;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		grow();
		data[p1] = o;
		p1++;
	}
	private void grow() {
		if(p1==p2){
			Object[] newArr = new Object[data.length+DEFALUT_SIZE];
			System.arraycopy(data, 0, newArr, 0, p1);
			System.arraycopy(data, p2+1, newArr, p2+1+DEFALUT_SIZE, data.length-1-p2);
			p2 = p2+DEFALUT_SIZE;
			data = newArr;
		}
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		if(p1==0){
			return null;
		}
		p1--;
		Object r = data[p1];
		data[p1] = null;
		compress();
		return r;
	}
	
	private void compress() {
		if(p2-p1>DEFALUT_SIZE){
			Object[] newArr = new Object[data.length-DEFALUT_SIZE];
			System.arraycopy(data, 0, newArr, 0, p1);
			System.arraycopy(data, p2+1, newArr, p2+1-DEFALUT_SIZE, data.length-1-p2);
			p2 = p2-DEFALUT_SIZE;
			data = newArr;
		}
	}
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if(p1==0){
			return null;
		}
		return data[p1-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		grow();
		data[p2]=o;
		p2--;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		if(p2==data.length-1){
			return null;
		}
		p2++;
		Object r = data[p2];
		data[p2] = null;
		compress();
		return r;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if(p2==data.length-1){
			return null;
		}
		return data[p2+1];
	}
	@Override
	public String toString() {
		StringBuffer buff = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			if(buff.length()!=0){
				buff.append(",");
			}
			if(data[i]==null){
				buff.append("null");
			}else{
				buff.append(data[i]);
			}
		}
		return buff.toString();
	}
	
	
	
}
