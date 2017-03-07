package com.coding.basic;

public class Queue {
	private int size=0;

	private ArrayList list = new ArrayList();

	public void enQueue(Object o){
		list.add(o);
		size++;
	}
	
	public Object deQueue(){
		if(size<=0){
			System.out.println("队列为空");
			return null;
		}
		size--;
		Object obj = list.get(0);
		list.remove(0);
		return obj;
	}
	
	public boolean isEmpty(){
		if(size==0){
			return true;
		}
		return false;
	}
	
	public int size(){
		return size;
	}

	public static void main(String[] args) {
		Queue q=new Queue();
		q.enQueue(1);
		q.enQueue(2);
		q.enQueue(3);
		q.enQueue(4);
		System.out.println(q.deQueue());
		System.out.println(q.deQueue());
	}
}
