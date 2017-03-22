
//package com.coding.basic;

public class QueueTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
       Queue q=new Queue();
       q.enQueue("add");
       q.enQueue("world");
       int length=q.size();
       System.out.println(length);
       System.out.println(q.deQueue());
       System.out.println(q.isEmpty());
	}

}

