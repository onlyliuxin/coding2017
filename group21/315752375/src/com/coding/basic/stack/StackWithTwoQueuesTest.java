package com.coding.basic.stack;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StackWithTwoQueuesTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		StackWithTwoQueues stackWithTwoQueues=new StackWithTwoQueues();
		stackWithTwoQueues.push(1);
		stackWithTwoQueues.push(2);
		stackWithTwoQueues.push(3);
		System.out.println("pop()"+stackWithTwoQueues.pop());
		System.out.println("pop()"+stackWithTwoQueues.pop());
		stackWithTwoQueues.push(4);
		stackWithTwoQueues.push(5);
		stackWithTwoQueues.push(6);
		while(!stackWithTwoQueues.isEmpty()){
			System.out.print(stackWithTwoQueues.pop()+"-");
		}
	}

}
