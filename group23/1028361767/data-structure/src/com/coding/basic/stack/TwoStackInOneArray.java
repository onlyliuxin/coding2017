package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	int front;
	int rear;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		checkInBound();
		data[front++] = o;
	}
	
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		checkOutBound(front);
		Object o = data[--front];
		data[front] = null;
		return o;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		checkOutBound(front);
		return data[front-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		checkInBound();
		data[data.length-rear-1] = o;
		rear++;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		checkOutBound(rear);
		int index = data.length-rear;
		Object o = data[index];
		data[index] = null;
		rear--;
		return o;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		checkOutBound(rear);
		return data[data.length-rear];
	}
	
	private Object[] resize(int len) {
		int newLen = len * 2;
		Object[] newData = new Object[newLen];
		if(front > 0){
			System.arraycopy(data, 0, newData, 0, front);
		}
		if(rear > 0){
			System.arraycopy(data, len-rear, newData, newLen-rear, rear);
		}
		return newData;
	}
	
	private void checkOutBound(int index){
		if(index <= 0){
			throw new IndexOutOfBoundsException();
		}
	}
	
	private void checkInBound() {
		if((front+rear) >= data.length){
			data = resize(data.length);
		}
	}
	
	public static void main(String[] args) {
		TwoStackInOneArray t = new TwoStackInOneArray();
		for(int i=0;i<25;i++){
			t.push1(i);
			t.push2(i);
		}
		System.out.println(t.front+t.rear);
		for(int i=0;i<t.data.length;i++){
			System.out.print(t.data[i]+",");
		}
	}
}