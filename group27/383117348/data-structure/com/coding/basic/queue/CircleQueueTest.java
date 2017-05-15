package com.coding.basic.queue;

import org.junit.Test;

public class CircleQueueTest {
	
	@Test
	public void testCircleQueue(){
		CircleQueue<String> queue = new CircleQueue<String>();
		queue.enQueue("aaaaa");
		queue.enQueue("bbbbb");
		queue.enQueue("ccccc");
		queue.enQueue("ddddd");
		queue.enQueue("eeeee");
		queue.enQueue("fffff");
		queue.enQueue("ggggg");
		queue.enQueue("hhhhh");
		queue.enQueue("iiiii");
		queue.enQueue("jjjjj");
		System.out.println("队列全满的时候:"+queue); 
	    System.out.println("删除一个元素后的队列：" + queue.deQueue());  

	    queue.enQueue("dddd");  
	    System.out.println("删除一个再加一个的时候:"+queue);  
	    System.out.println("队列满时的长度：" + queue.size());  
 
	    queue.deQueue();  
	    
	    System.out.println("删除一个的时候:"+queue);  
	}
	
}
