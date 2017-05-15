package com.github.orajavac.coding2017.basic.stack;


/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 * @author liuxin
 *
 */
public class TwoStackInOneArray {
	Object[] data = new Object[10];
	//栈顶指针1
	private int front = 0;  
	//栈顶指针2
	private int rear = data.length-1;
	
	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if (data[front] == null){
			data[front] = o;
			front++;
		}else{
			grow(data,10);
			data[front] = o;
			front++;
		}
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		Object o = null;
		if (front !=0 ){
			front--;
			o = data[front];
			data[front] = null;
		}else{
			throw new RuntimeException("栈底已经没有元素了");
		}
		return o;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		return data[front-1];
	}
	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if (data[rear] == null){
			data[rear] = o;
			rear--;
		}else{
			grow(data,10);
			data[rear] = o;
			rear--;
		}
	}
	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		Object o = null;
		if (rear !=data.length-1 ){
			rear++;
			o = data[rear];
			data[rear] = null;
		}else{
			throw new RuntimeException("栈底已经没有元素了");
		}
		return o;
	}
	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		return data[rear+1];
	}
	
	public void grow(Object[] elementData,int size){
		Object[] target = new Object[size+elementData.length];
		int n = target.length;
		int m = 0;
		for (int i=0;i<front;i++){
			target[i]=elementData[i];
			m = i;	//5
		}
		System.out.println("m = "+m);
		
		for (int j=elementData.length-1;j>rear;j--){
			n--;
			target[n]=elementData[j];
		}
		n--;	//在这里 n-- m++ 是因为在 push1 push2 时候，先push进去，再++的
		rear=n;
		m++;
		front=m;
		this.data=target;
	}
	
	public void iteration(){
		for (int i=0;i<data.length;i++){
			System.out.print(data[i]+",");
		}
		System.out.println();
	}
}
