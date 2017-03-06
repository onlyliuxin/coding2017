package com.github.chaoswang.learning.java.collection.myown;

import java.util.NoSuchElementException;


public class MyQueue<E> {
	private int size = 0;
	private int capacitySize;
	private OneElement first = null;
	private OneElement last = null;//可以改为用自己的LinkedList实现
	
	public MyQueue(int capacitySize){
		this.capacitySize = capacitySize;
	}
	
	//插入队尾   队列满时抛异常
	public void add(E element){
		if(size+1 > capacitySize){
			throw new IllegalStateException("over capacity.");
		}
		addLast(element);
	}
	
	//插入队尾   不抛异常，会返回是否插入成功
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
	
	//移除队头元素，队列为空时抛异常
	public E remove(){
		if(size == 0){
			throw new NoSuchElementException();
		}
		return removeFirst();
	}
	
	//移除队头元素，队列为空时返回null，所以queue一般不让插入null元素
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
	
	//返回队头元素，但是不移除，队列为空时抛异常
	public E element(){
		if(size == 0){
			throw new NoSuchElementException();
		}
		return first.element;
	}
	
	//返回队头元素，但是不移除，队列为空时返回null
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
