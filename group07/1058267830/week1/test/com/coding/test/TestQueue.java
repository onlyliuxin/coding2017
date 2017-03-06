package com.coding.test;

import org.junit.Test;

import com.coding.basic.Node;
import com.coding.basic.Queue;

public class TestQueue {

	@Test
	public void test() {
		Queue queue = new Queue();
		queue.enQueue("a");
		queue.enQueue("b");
		queue.enQueue("c");
		System.out.println(queue.size());
		System.out.println("--------------");
		Node node = (Node)queue.deQueue();
		System.out.println(node.getData());
		Node node1 = (Node)queue.deQueue();
		System.out.println(node1.getData());
		System.out.println(queue.isEmpty());
		System.out.println(queue.size());
	}

}
