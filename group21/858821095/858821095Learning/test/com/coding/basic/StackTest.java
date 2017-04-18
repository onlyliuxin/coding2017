package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class StackTest {

	Tstack s = new Tstack();
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testPush() {
		
	}

	@Test
	public void testPop() {
		s.push(5);;
		s.push(8);
		s.push(null);
		Assert.assertEquals(3, s.size());
		Assert.assertEquals(null, s.pop());
	}

	@Test
	public void testPeek() {
		s.push(5);;
		s.push(8);
		s.push(null);
		Assert.assertEquals(3, s.size());
		Assert.assertEquals(null, s.peek());
		Assert.assertEquals(null, s.peek());
	}

	@Test
	public void testIsEmpty() {
		Assert.assertEquals(true, s.isEmpty());
	}
	
	@Test
	public void testClear() {
		s.push(5);;
		s.push(8);
		s.push(null);
		s.clear();
		Assert.assertEquals(0, s.size());
	}

	@Test
	public void testSize() {
		s.push(5);;
		s.push(8);
		s.push(null);
		Assert.assertEquals(3, s.size());
	}

	@Test
	public void testSearch() {
		s.push(5);;
		s.push(8);
		s.push(null);
		Assert.assertEquals(0, s.search(5));
	}

}
