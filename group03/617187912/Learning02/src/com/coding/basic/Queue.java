package com.coding.basic;


public class Queue {
	private LinkedList linkedList = new LinkedList();

	public void enQueue(Object o) {
		linkedList.add(o);
	}

	public Object deQueue() {
		return linkedList.removeFirst();
	}

	public boolean isEmpty() {
		return linkedList.size() ==0;
	}

	public int size() {
		return linkedList.size();
	}
	public static void main(String[] args) {
		Queue que = new Queue();
		que.enQueue(10);
		que.enQueue(11);
		que.enQueue(12);
		System.out.println(que.deQueue());
		System.out.println(que.isEmpty());
		que.deQueue();
		que.deQueue();
		System.out.println(que.isEmpty());
	}
}
