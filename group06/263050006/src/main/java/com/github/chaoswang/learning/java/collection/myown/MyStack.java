package com.github.chaoswang.learning.java.collection.myown;

import java.util.Arrays;
import java.util.EmptyStackException;

public class MyStack<E> {
	private int size = 0;
	private int initialSize;
	private Object[] elements = null;//���Ը�Ϊ���Լ���ArrayList��ʵ��
	
	public MyStack(int initialSize){
		this.initialSize = initialSize;
		elements = new Object[initialSize];
	}
	
	//ѹջ
	public void push(E element){
		//�ﵽ�������ޣ���initialSize����100%
		if(++size == elements.length){
			elements = Arrays.copyOf(elements, size + initialSize);
		}
		elements[size - 1] = element;
	}
	
	//�ж�ջ�Ƿ�Ϊ��
	public boolean empty(){
		return size <= 0? true : false;
	}
	
	public int size(){
		return size;
	}
	
	//�鿴ջ��Ԫ��
	public E peek(){
		if(size == 0){
			throw new EmptyStackException();
		}
		return (E)elements[size - 1];
	}
	
	//����ջ��Ԫ��
	public E pop(){
		if(size == 0){
			throw new EmptyStackException();
		}
		E removed = (E)elements[size -1];
		elements[size -1] = null;
		size--;
		return removed;
	}
	
	public int search(E element) {
		int index = 0;
		for (int i = 0; i < size - 1; i++) {
			if (element.equals(elements[i])) {
				index = (size -1) - i;
				break;
			}
		}
		return index;
	}
}
