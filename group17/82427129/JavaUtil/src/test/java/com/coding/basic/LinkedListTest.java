package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	private LinkedList<Integer> list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testAddObject() {
		list.add(100);
		Assert.assertEquals(1, list.size());
		list.add(100);
		Assert.assertEquals(2, list.size());
		list.add(100);
		Assert.assertEquals(3, list.size());
	}

	@Test
	public void testAddIntObject() {
		for (int i = 0; i < 5; i++) {
			list.add(i, i);
		}
		Assert.assertEquals(5, list.size());
	}

	@Test
	public void testGet() {
		
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
