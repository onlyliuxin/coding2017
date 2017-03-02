package com.github.chaoswang.learning.java.collection.myown;

import java.util.NoSuchElementException;


public class MyQueue<E> {
	private int size = 0;
	private int capacitySize;
	private OneElement first = null;
	private OneElement last = null;//���Ը�Ϊ���Լ���LinkedListʵ��
	
	public MyQueue(int capacitySize){
		this.capacitySize = capacitySize;
	}
	
	//�����β   ������ʱ���쳣
	public void add(E element){
		if(size+1 > capacitySize){
			throw new IllegalStateException("over capacity.");
		}
		addLast(element);
	}
	
	//�����β   �����쳣���᷵���Ƿ����ɹ�
	public boolean offer(E element){
		if(size+1 > capacitySize){
			return false;
		}
		addLast(element);
		return true;
	}
	
	private void addLast(E element){
		OneElement tmp = new OneElement(element, null);
		if(last == null){
			first = tmp;
		}else{
			last.next = tmp;;
		}
		last = tmp;
		size++;
	}
	
	//�Ƴ���ͷԪ�أ�����Ϊ��ʱ���쳣
	public E remove(){
		if(size == 0){
			throw new NoSuchElementException();
		}
		return removeFirst();
	}
	
	//�Ƴ���ͷԪ�أ�����Ϊ��ʱ����null������queueһ�㲻�ò���nullԪ��
	public E poll(){
		if(size == 0){
			return null;
		}
		return removeFirst();
	}
	
	private E removeFirst(){
		OneElement tmp = first;
		first = first.next;
		size--;
		return tmp.element;
	}
	
	//���ض�ͷԪ�أ����ǲ��Ƴ�������Ϊ��ʱ���쳣
	public E element(){
		if(size == 0){
			throw new NoSuchElementException();
		}
		return first.element;
	}
	
	//���ض�ͷԪ�أ����ǲ��Ƴ�������Ϊ��ʱ����null
	public E peek(){
		if(size == 0){
			return null;
		}
		return first.element;
	}
	
	private class OneElement{
		private E element = null;
		private OneElement next = null;

		public OneElement(E element, OneElement next) {
			this.element = element;
			this.next = next;
		}
	}
}
