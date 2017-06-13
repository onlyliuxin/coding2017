package com.coding.basic;

/**
 * Queue 实现 第14小组 296933284
 *
 * @author Tonnyson
 *
 */
public class Queue<T> {
	
	private LinkedList<T> elementData = new LinkedList<>();

	public void enQueue(T element){
		elementData.addLast(element);
	}

	public T deQueue(){
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size() == 0;
	}

	public int size(){
		return elementData.size();
	}
}
