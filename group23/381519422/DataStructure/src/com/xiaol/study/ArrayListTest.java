package com.xiaol.study;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ArrayListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		ArrayList list = new ArrayList();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		list.add(6);
		assertEquals(12, list.size());
	}

	@Test
	public void testAddIntObject() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(0, 3);
		assertEquals(3, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.size());
	}

	@Test
	public void testGet() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(0, 3);
		assertEquals(3, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.size());
	}

	@Test
	public void testRemove() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(0, 3);
		assertEquals(3, list.remove(0));
		assertEquals(1, list.remove(0));
		assertEquals(2, list.remove(0));
		assertEquals(0, list.size());
	}

	@Test
	public void testSize() {
		ArrayList list = new ArrayList();
		list.add(1);
		list.add(2);
		list.add(0, 3);
		assertEquals(3, list.size());
	}

}
