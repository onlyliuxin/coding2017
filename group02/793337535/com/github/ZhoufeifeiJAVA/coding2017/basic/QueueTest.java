package com.github.ZhoufeifeiJAVA.coding2017.basic;
public class QueueTest{
	public static void sop(Object o){
		System.out.println(o);
	}
	public static void main(String[] args){
		Queue q = new Queue();
		q.enQueue("String0");
		q.enQueue("String1");
		q.enQueue("String2");
		sop("the queue is not empty "+q.isEmpty());
		sop("the size of queue is "+q.size());
		sop("out queue "+q.deQueue());
		sop("out queue "+q.deQueue());
		sop("the size of queue is "+q.size());
	}
}