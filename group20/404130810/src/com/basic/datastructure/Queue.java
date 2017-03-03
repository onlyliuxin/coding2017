package com.basic.datastructure;

public class Queue {
	LinkedList list = new LinkedList();
	private int size;
	
	public void enQueue(Object o){
		list.add(o);
		size ++;
	}
	
	public Object deQueue(){
		size --;
		return list.removeLast();
	}
	
	public boolean isEmpty(){
		return list.size() == 0;
	}
	
	public int size(){
		return size;
	}
	
	public static void main(String[] args) {
		Queue queue = new Queue();
		for (int i = 0; i < 10; i++) {
			queue.enQueue(i);
		}
		queue.deQueue();
		System.out.println("Finished");
	}
}
