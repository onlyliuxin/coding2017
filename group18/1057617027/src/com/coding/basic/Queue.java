package com.coding.basic;

public class Queue {
	
	private Object[] elementData = new Object[100];
	private int size;
	public void enQueue(Object o){
		elementData[size++] = o;
	}
	
	public Object deQueue(){
		elementData[0] = null;
		System.arraycopy(elementData, 1, elementData, 0, elementData.length-1);
		size--;
		return elementData;
	}
	
	public boolean isEmpty(){
		return size==0;
	}
	
	public int size(){
		return size;
	}
	public static void main(String args[]){
		Queue q = new Queue();
		q.enQueue("wo");
		q.enQueue("wo1");
		q.enQueue("wo2");
		q.deQueue();
		for(int i = 0;i<q.size();i++)
		System.out.println(q.elementData[i]);
	}
}
