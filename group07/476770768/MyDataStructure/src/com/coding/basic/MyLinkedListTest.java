package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyLinkedListTest {

	@Test
	public void testAddObject() {
		MyLinkedList mll = new MyLinkedList();
		assertEquals(0, mll.size());
		mll.add(new Integer(1));
		assertEquals(1, mll.size());
	}

	@Test
	public void testAddIntObject() {
		MyLinkedList mll = new MyLinkedList();
		mll.add(0, new Integer(1));
		assertEquals(1, mll.size());
		int tmp = 0;
		try {
			mll.add(4, new Integer(4));
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
	}

	@Test
	public void testGet() {
		MyLinkedList mll = new MyLinkedList();
		mll.add(new Object());
		assertNotNull(mll.get(0));
		int tmp = 0;
		try {
			mll.get(4);
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
	}

	@Test
	public void testRemove() {
		MyLinkedList mll = new MyLinkedList();
		mll.add(new Object());
		mll.remove(0);
		assertEquals(mll.size(),0);	
	}

	@Test
	public void testSize() {
		MyLinkedList mll = new MyLinkedList();
		assertEquals(0, mll.size());	
	}

	@Test
	public void testIsEmpty() {
		MyLinkedList mll = new MyLinkedList();
		assertTrue(mll.isEmpty());
		mll.add(new Object());
		assertFalse(mll.isEmpty());
	}

}
