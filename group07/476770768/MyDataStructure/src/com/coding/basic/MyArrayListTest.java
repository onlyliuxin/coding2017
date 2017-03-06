package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class MyArrayListTest {


	@Test
	public void testAddObject() {
		MyArrayList mal = new MyArrayList();
		assertEquals(0, mal.size());
		mal.add(new Integer(1));
		assertEquals(1, mal.size());
	}

	@Test
	public void testAddIntObject() {
		MyArrayList mal = new MyArrayList();
		mal.add(0, new Integer(1));
		assertEquals(1, mal.size());
		int tmp = 0;
		try {
			mal.add(4, new Integer(4));
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
		
	}

	@Test
	public void testGet() {
		MyArrayList mal = new MyArrayList();
		mal.add(new Integer(1));
		assertEquals((Integer)mal.get(0),new Integer(1));
		int tmp = 0;
		try {
			mal.get(4);
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
	}

	@Test
	public void testRemove() {
		MyArrayList mal = new MyArrayList();
		mal.add(new Integer(1));
		assertEquals((Integer)mal.get(0),new Integer(1));
		assertEquals(mal.size(),1);
	}

	@Test
	public void testSize() {
		MyArrayList mal = new MyArrayList();
		assertEquals(0, mal.size());
	}

	@Test
	public void testIsEmpty() {
		MyArrayList mal = new MyArrayList();
		assertTrue(mal.isEmpty());
		mal.add(new Integer(1));
		assertFalse(mal.isEmpty());
	}

}
