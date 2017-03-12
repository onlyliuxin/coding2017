package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.first.impl.Queue;

public class QueueTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testEnQueue() {
		Queue queue = new Queue();
		queue.enQueue("1");
		queue.enQueue("2");
		queue.enQueue("3");
		queue.enQueue("4");
		queue.enQueue("5");
		assertEquals(5, queue.size());
	}

	@Test
	public void testDeQueue() {
		Queue queue = new Queue();
		queue.enQueue("1");
		queue.enQueue("2");
		queue.enQueue("3");
		queue.enQueue("4");
		queue.enQueue("5");
		for(int i=queue.size();i>0;i=queue.size()){
			if(!queue.isEmpty()){
				System.out.print("i:");
				System.out.println(queue.deQueue());
			}
		}
		assertEquals(0, queue.size());
	}

	@Test
	public void testIsEmpty() {
		Queue queue = new Queue();
		queue.enQueue("1");
		queue.enQueue("2");
		queue.enQueue("3");
		queue.enQueue("4");
		queue.enQueue("5");
		for(int i=queue.size();i>0;i=queue.size()){
			if(!queue.isEmpty()){
				System.out.print("i:");
				System.out.println(queue.deQueue());
			}
		}
		assertEquals(0, queue.size());
	}

	@Test
	public void testSize() {
		Queue queue = new Queue();
		queue.enQueue("1");
		queue.enQueue("2");
		queue.enQueue("3");
		queue.enQueue("4");
		queue.enQueue("5");
		for(int i=queue.size();i>0;i=queue.size()){
			if(!queue.isEmpty()){
				System.out.print("i:");
				System.out.println(queue.deQueue());
			}
		}
		assertEquals(0, queue.size());
	}

}
