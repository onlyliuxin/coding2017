package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.coding.basic.ArrayList;

//
public class ArrayListTest {

	@Test
	public void testAddObject() {
		ArrayList al = new ArrayList();
		assertEquals(10, al.size());
		al.add(new Integer(1));
		System.out.print(al.get(0));
	}
	
	@Test
	public void testAddIntObject() {
		ArrayList al = new ArrayList();
		al.add(0, new Integer(1));
		assertEquals(10, al.size());
		int tmp = 0;
		try {
			al.add(4, new Integer(4));
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
		
	}

	@Test
	public void testGet() {
		ArrayList al = new ArrayList();
		al.add(new Integer(1));
		assertEquals((Integer)al.get(0),new Integer(1));
		int tmp = 0;
		try {
			al.get(4);
		} catch (IndexOutOfBoundsException e) {
			tmp = 1;
			assertEquals(tmp, 1);
		}
	}

	@Test
	public void testRemove() {
		ArrayList al = new ArrayList();
		al.add(new Integer(1));
		assertEquals((Integer)al.get(0),new Integer(1));
		assertEquals(al.size(),10);
	}

	@Test
	public void testSize() {
		ArrayList al = new ArrayList();
		assertEquals(10, al.size());
	}

	@Test
	public void testIsEmpty() {
		ArrayList al = new ArrayList();
		assertTrue(al.isEmpty());
		al.add(new Integer(1));
		assertFalse(al.isEmpty());
	}
}
