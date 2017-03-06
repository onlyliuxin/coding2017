package com.coding.basic.impl;

import com.coding.basic.Iterator;

/**
 * 队列简单实现
 * @author 240094626
 *
 */
public class Queue {
	/**队列元素容器对象*/
	LinkedList elementData = new LinkedList();

	/**
	 * 入队列
	 * @param o
	 */
	public void enQueue(Object o){	
		elementData.add(o);
	}
	
	/**
	 * 出队列：先进先出，故取出链表首个节点
	 * @return
	 */
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		if(elementData.size() > 0 ){
			return false;
		}
		return true;
	}
	
	public int size(){
		return elementData.size();
	}
	
	
	@Override
	public String toString() {
		return "Queue {elementData=" + elementData + "}";
	}

	public Iterator iterator(){
		return new QueueIterator();
	}
	
	private class QueueIterator implements Iterator{

		int index;
		
		public QueueIterator() {
			index = 0;
		}

		@Override
		public boolean hasNext() {
			if(index < elementData.size()){
				return true;
			}
			return false;
		}

		@Override
		public Object next() {
			return elementData.get(index++);
		}
		
	}
}
