package com.github.xiaozi123.coding2017.basic;

public class Queue {
	private LinkedList elementData=new LinkedList();
	
	public void enQueue(Object o){		
		elementData.add(o);
	}
	
	public Object deQueue(){
		return elementData.removeFirst();
	}
	
	public boolean isEmpty(){
		return elementData.size()==0;
	}
	
	public int size(){
		return elementData.size();
	}
	
	public static void main(String[] args) {
		Queue queue=new Queue();
		if (queue.isEmpty()) {
			System.out.println("���������ǿա�");
		}
		
		int n=3;
		for (int i = 0; i < n; i++) {
			queue.enQueue(i);
		}
		System.out.println("����������"+queue.size()+"����");
		System.out.print("����Ӧ���ǣ�0---");
		System.out.println(queue.deQueue());
		System.out.print("����ڶ�����Ӧ���ǣ�1---");
		System.out.println(queue.deQueue());
		System.out.print("����ڶ�����Ӧ���ǣ�2---");
		System.out.println(queue.deQueue());
		
		
		
		
	}
	
	
}
