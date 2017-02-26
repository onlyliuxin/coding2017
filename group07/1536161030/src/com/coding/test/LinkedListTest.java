package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.LinkedList;

//
public class LinkedListTest{
	@Test
	public void testAddObject() {
		LinkedList list = new LinkedList();
		assertEquals(0, list.size());
		list.add(new Integer(1));
		assertEquals(1, list.size());
	}

	@Test
	public void testAddIntObject() {
		LinkedList list = new LinkedList();
		list.add(0, new Integer(1));
		assertEquals(1, list.size());
		int tmp = 0;
		try {
			list.add(4, new Integer(4));
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
	}

	@Test
	public void testGet() {
		LinkedList list = new LinkedList();
		list.add(new Object());
		assertNotNull(list.get(0));
		int tmp = 0;
		try {
			list.get(4);
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
	}

	@Test
	public void testRemove() {
		LinkedList list = new LinkedList();
		list.add(new Object());
		list.remove(0);
		assertEquals(list.size(),0);	
	}

	@Test
	public void testSize() {
		LinkedList list = new LinkedList();
		assertEquals(0, list.size());	
	}

	@Test
	public void testIsEmpty() {
		LinkedList list = new LinkedList();
		assertTrue(list.isEmpty());
		list.add(new Object());
		assertFalse(list.isEmpty());
}
}
