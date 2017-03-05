package com.ikook.basic_data_structure;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

/**
 * 此单元测试只测试了正常情况，一些异常情况没有测试。
 * @author ikook
 */
public class MyStackTest {

	private MyStack stack;

	@Before
	public void setUp() {
		stack = new MyStack();
		stack.push(111);
		stack.push("222");
		stack.push(333);
		stack.push(new Date());
		stack.push("555");
	}

	@Test
	public void testPush() {
		stack.push(93554);
		assertEquals(6, stack.size());
	}

	@Test
	public void testPop() {
		assertEquals("555", stack.pop());
		assertEquals(4, stack.size());
		
		assertEquals(new Date(), stack.pop());
	}

	@Test
	public void testGetTop() {
		assertEquals("555", stack.getTop());
	}

	@Test
	public void testIsEmpty() {
		assertEquals(false, stack.isEmpty());
		
		MyStack stack = new MyStack();
		assertEquals(true, stack.isEmpty());
	}

}
