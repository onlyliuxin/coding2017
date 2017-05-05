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
			System.out.println("队列现在是空。");
		}
		
		int n=3;
		for (int i = 0; i < n; i++) {
			queue.enQueue(i);
		}
		System.out.println("队列现在有"+queue.size()+"个数");
		System.out.print("队首应该是：0---");
		System.out.println(queue.deQueue());
		System.out.print("队伍第二个数应该是：1---");
		System.out.println(queue.deQueue());
		System.out.print("队伍第二个数应该是：2---");
		System.out.println(queue.deQueue());
		
		
		
		
	}
	
	
}
