package com.xiaol.study;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		assertEquals(1, ll.get(0));
		assertEquals(2, ll.get(1));
		assertEquals(3, ll.get(2));
	}

	@Test
	public void testAddIntObject() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(3, 0);
		assertEquals(1, ll.get(0));
		assertEquals(2, ll.get(1));
		assertEquals(3, ll.get(2));
		assertEquals(0, ll.get(3));
		assertEquals(4, ll.size());
	}

	@Test
	public void testGet() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(3, 0);
		assertEquals(1, ll.get(0));
		assertEquals(2, ll.get(1));
		assertEquals(3, ll.get(2));
		assertEquals(0, ll.get(3));
		assertEquals(4, ll.size());
	}

	@Test
	public void testRemove() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(0, 0);
		ll.remove(0);
		assertEquals(1, ll.get(0));
		assertEquals(2, ll.get(1));
		assertEquals(3, ll.get(2));
	}

	@Test
	public void testSize() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.add(0, 0);
		assertEquals(4, ll.size());
	}

	@Test
	public void testAddFirst() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.addFirst(001);
		ll.addFirst(002);
		assertEquals(002, ll.get(0));
		assertEquals(001, ll.get(1));
		assertEquals(1, ll.get(2));
		assertEquals(2, ll.get(3));
	}

	@Test
	public void testAddLast() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.addLast(001);
		ll.addLast(002);
		assertEquals(1, ll.get(0));
		assertEquals(2, ll.get(1));
		assertEquals(001, ll.get(2));
		assertEquals(002, ll.get(3));
	}

	@Test
	public void testRemoveFirst() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.removeFirst();
		ll.removeFirst();
		assertEquals(3, ll.get(0));
		assertEquals(1, ll.size());
	}

	@Test
	public void testRemoveLast() {
		LinkedList ll = new LinkedList();
		ll.add(1);
		ll.add(2);
		ll.add(3);
		ll.removeLast();
		assertEquals(1, ll.get(0));
		assertEquals(2, ll.get(1));
		assertEquals(2, ll.size());
	}

}
