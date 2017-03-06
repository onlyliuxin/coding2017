package com.coding.basic;

public class Queue<E> {

	private LinkedList<E> elementData = new LinkedList();

	/**
	 * 进队列
	 * @param o
	 */
	public void enQueue(E o){
		elementData.addLast(o);//添加到队尾
	}

	/**
	 * 出队列
	 * @return
	 */
	public E deQueue(){
		return elementData.removeFirst();//移除队首
	}
	
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	
	public int size(){
		 return elementData.size();
	}
}
