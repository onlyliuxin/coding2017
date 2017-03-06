package com.coding.basic;
/**
 * @author Hipple
 * @Time：2017年2月23日 下午11:00:00
 * @version 1.0
 */

public class Queue {
	private LinkedList elementData = new LinkedList();
	
	public Queue(){
		
	}
	
	public void enQueue(Object o){
		elementData.addLast(o);
	}
	
	public Object deQueue(){
		elementData.getFirst();
		return null;
	}
	
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	
	public int size(){
		return elementData.size();
	}
}
