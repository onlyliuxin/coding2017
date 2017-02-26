package test_data_structure;



import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import data_structure.MyQueue;

public class TestMyQueue {
	MyQueue queue;

	@Before
	public void setUp() throws Exception {
		queue =new MyQueue();
		System.out.println("开始测试");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("结束测试");
	}

	@Test
	public void testEnQueue() {
		queue.enQueue("hello");
	}

	@Test
	public void testDeQueue() {
		queue.enQueue("hello");
		queue.enQueue("world");
		Assert.assertEquals("hello",queue.deQueue());
	}

	@Test
	public void testIsEmpty() {
		queue.enQueue("hello");
		Assert.assertEquals(false,queue.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0,queue.size());
	}

}
