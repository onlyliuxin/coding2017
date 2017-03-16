package com.coding.basic;

public class Queue<E> {
	private DoubleNodeLinkedList<E> elementData = new DoubleNodeLinkedList<E>();
	
	public void enQueue(E o){
		elementData.addLast(o);
	}
	
	public E deQueue(){
		return elementData.removeLast();
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}
	
	public int size(){
		return elementData.size();
	}
}
