package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.ArrayList;
import com.coding.Iterator;

public class ArrayListTest {
	
	private static ArrayList list = new ArrayList();

	@Before
	public void setUp() throws Exception {
		list = new ArrayList();
		list.add("1111");
		list.add("2222");
		list.add("3333");
	}

	@After
	public void tearDown() throws Exception {
		list = null;
	}

	@Test
	public void testAddObject() {
		list.add("4444");
		assertEquals("4444", list.get(3));
		assertEquals(4, list.size());
	}

	@Test
	public void testAddIntObject() {
		list.add(1, "4444");
		assertEquals("4444", list.get(1));
		assertEquals(4, list.size());
		assertEquals("2222", list.get(2));
	}

	@Test
	public void testGet() {
		assertEquals("1111", list.get(0));
	}

	@Test
	public void testRemove() {
		Object sss = list.remove(1);
		assertEquals(2, list.size());
		assertEquals("2222", sss);
	}

	@Test
	public void testSize() {
		assertEquals(3, list.size());
	}

	@Test
	public void testIterator() {
		Iterator it = list.iterator();
		assertEquals(true, it.hasNext());
		assertEquals("1111", it.next());
		assertEquals("2222", it.next());
		assertEquals("3333", it.next());
		assertEquals(false, it.hasNext());
	}

}
