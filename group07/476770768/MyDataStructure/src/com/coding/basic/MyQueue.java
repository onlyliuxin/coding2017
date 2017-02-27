package com.coding.basic;

public class MyQueue {
	
	private MyLinkedList elementData = new MyLinkedList();
	
	public void enQueue(Object o){
		elementData.add(o);
	}
	
	public Object deQueue(){
		//if queue is empty, element.size()-1 = -1
		//MyLinkedList will throw exception
		Object tmp = elementData.remove(elementData.size()-1);
		return tmp;
	}
	
	public boolean isEmpty(){
		return elementData.isEmpty();
	}
	
	public int size(){
		return elementData.size();
	}
	
	public MyIterator iterator() {
		return elementData.iterator();
	}

}
