package com.coding.basic.stack;

/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author gdw
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	int peek1=-1;
	int peek2=data.length;
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		ensureCompacity();
		data[++peek1]=o;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		Object object=null;
		if(peek1!=-1){
			object=data[peek1--];
		}
		return object;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		Object object=null;
		if(peek1!=-1){
			object=data[peek1];
		}
		return object;
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		ensureCompacity();
		data[--peek2]=o;
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		Object object=null;
		if(peek2!=-1){
			object=data[peek2++];
		}
		return object;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		Object object=null;
		if(peek2!=-1){
			object=data[peek2];
		}
		return object;
	}
	public void ensureCompacity() {
		if(peek1+1==peek2){
			int len=data.length;
			Object[] newObject=new Object[len*2];
			System.arraycopy(data, 0, newObject, 0, peek1+1);
			System.arraycopy(data, peek2, newObject, len*2-len+peek2, len-peek2);
			data=newObject;
			peek2=len*2-len+peek2;
		}
	}
	
}
