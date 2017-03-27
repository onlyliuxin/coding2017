package my.collection.linearTest;

import my.collection.linear.MyQueue;

public class MyQueueTest {

	public static void main(String[] args) {
		MyQueue mq = new MyQueue();
		
		System.out.println(mq.isEmpty());
		
		mq.enQueue("a");
		System.out.println(mq.isEmpty());
		
		mq.enQueue("s");
		mq.enQueue("d");
		
		System.out.println(mq.size());
		
		mq.deQueue();
		System.out.println(mq.size());
		
	}

}
