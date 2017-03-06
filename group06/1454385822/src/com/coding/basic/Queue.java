package com.coding.basic;

public class Queue {
	
	private LinkedList elementData = new LinkedList();
	private int num = 0;
	
	public void enQueue(Object o){	
		elementData.add(o);
		num++;
	}
	
	public Object deQueue(){
		num--;
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return num <= 0;
	}
	
	public int size(){
		return num;
	}
	
	public static void main(String[] args) {
		Queue queue = new Queue();
		queue.enQueue(1);
		queue.enQueue(2);
		queue.enQueue(3);
		queue.enQueue(4);
		System.out.println("当前队列的长度为:"+queue.size());
		while(!queue.isEmpty()){
			System.out.println(queue.deQueue());
		}
		
	}
	
}



