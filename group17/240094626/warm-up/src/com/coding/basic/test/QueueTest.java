package com.coding.basic.test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.coding.basic.impl.Queue;

/**
 * Queue 简单测试
 * @author 240094626
 *
 */
public class QueueTest {

	@Test
	public void test() {
		Queue queue = new Queue();
		
		System.out.println("******测试enQueue(Object o):入队列元素a,b,c******");
		queue.enQueue("a");
		queue.enQueue("b");
		queue.enQueue("c");
		System.out.println("queue:"+queue.toString());
		
		// 断言队列不为空
		assertEquals(false,queue.isEmpty());
		
		// 断言出队列是a
		System.out.println("******测试deQueue(Object o):出队列元素a******");
		assertEquals("a",queue.deQueue());
		System.out.println("queue:"+queue.toString());
		
		// 断言出队列是b
		System.out.println("******测试deQueue(Object o):出队列元素b******");
		assertEquals("b",queue.deQueue());
		System.out.println("queue:"+queue.toString());
		
		// 断言出队列是c
		assertEquals("c",queue.deQueue());
		
		
		
		
	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(QueueTest.class);
		for(Failure failure : result.getFailures()){
			System.out.println(failure.toString());
		}
		System.out.println("test success!:"+result.wasSuccessful());
	}
}
