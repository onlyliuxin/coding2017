package com.sprint.datastructure;

import java.util.EmptyStackException;
/**
 * 用一个数组实现两个栈
 * 将数组的起始位置看作是第一个栈的栈底，将数组的尾部看作第二个栈的栈底，压栈时，栈顶指针分别向中间移动，直到两栈顶指针相遇，则扩容。
 *
 */
public class TwoStackInOneArray {
	Object[] data;
	int capacity;
	int front;
	int rear;

	public TwoStackInOneArray() {
		data = new Object[10];
		front = 0;
		rear = 9;
		capacity = 10;
	}	

	/**
	 * 向第一个栈中压入元素
	 * @param o
	 */
	public void push1(Object o){
		if (front == rear) {
			expandCapacity(capacity * 2 + 1);
		}
		data[front] = o;
		front++;
	}

	private void expandCapacity(int newCapacity) {
		Object[] old = data;
		//将old中数据转移到data中
		data = new Object[newCapacity];
		//将前栈数据复制进去
		for (int i = 0; i < front; i++) {
			data[i] = old[i];
		}
		//将后栈数据复制进去
		int size = this.capacity - rear - 1;
		for (int i = 0; i < size; i++) {
			data[newCapacity - 1] = old[capacity - 1];
		}
		capacity = newCapacity;
		rear = newCapacity - size - 1;
	}
	/**
	 * 从第一个栈中弹出元素
	 * @return
	 */
	public Object pop1(){
		Object old = null;
		if (front <= 0) {
			throw new EmptyStackException();
		} 
		old = data[--front];
		data[front] = null;
		return old;
	}
	
	/**
	 * 获取第一个栈的栈顶元素
	 * @return
	 */
	
	public Object peek1(){
		if (front == 0)
			return null;
		//返回data[0]
		int header = (int)data[0];
		//将前栈元素前移
/*		for (int i = 1; i < front; i++) {
			data[i - 1] = data[i];
		}
		front--;
*/
		return header;
	}

	/*
	 * 向第二个栈压入元素
	 */
	public void push2(Object o){
		if (front == rear) {
			expandCapacity(capacity * 2 + 1);
		}
		data[rear] = o;
		rear--;
	}

	/**
	 * 从第二个栈弹出元素
	 * @return
	 */
	public Object pop2(){
		Object old = null;
		if (rear >= capacity - 1) {
			throw new EmptyStackException();
		} 
		old = data[++rear];
		data[rear] = null;
		return old;
	}

	/**
	 * 获取第二个栈的栈顶元素
	 * @return
	 */
	
	public Object peek2(){
		if (rear >= capacity - 1)
			return null;
		int header = (int)data[capacity - 1];
		return header;
	}
	
}
