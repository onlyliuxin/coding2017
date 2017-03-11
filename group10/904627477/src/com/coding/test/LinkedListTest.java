package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.Iterator;
import com.coding.LinkedList;

public class LinkedListTest {
	
	private static LinkedList link;

	@Before
	public void setUp() throws Exception {
		link = new LinkedList();
		link.add("111");
		link.add("222");
		link.add("333");
	}

	@After
	public void tearDown() throws Exception {
		link = null;
	}

	@Test
	public void testAddObject() {
		link.add("444");
		assertEquals(4, link.size());
		assertEquals("444", link.get(3));
		link = new LinkedList();
		link.add("000");
		assertEquals(1, link.size());
		assertEquals("000", link.get(0));
	}

	@Test
	public void testAddIntObject() {
		link.add(2,"444");
		assertEquals(4, link.size());
		assertEquals("444", link.get(2));
		assertEquals("333", link.get(3));
		assertEquals("222", link.get(1));
		link = new LinkedList();
		link.add(0,"000");
		assertEquals(1, link.size());
		assertEquals("000", link.get(0));
	}

	@Test
	public void testGet() {
		assertEquals("222", link.get(1));
	}

	@Test
	public void testRemove() {
		Object obj = link.remove(1);
		assertEquals("222", obj);
		assertEquals("333", link.get(1));
		assertEquals(2, link.size());
	}

	@Test
	public void testSize() {
		assertEquals(3, link.size());
	}

	@Test
	public void testAddFirst() {
		link.addFirst("000");
		assertEquals(4, link.size());
		assertEquals("000", link.get(0));
		link = new LinkedList();
		link.addFirst("000");
		assertEquals(1, link.size());
		assertEquals("000", link.get(0));
	}

	@Test
	public void testAddLast() {
		link.addLast("444");
		assertEquals(4, link.size());
		assertEquals("444", link.get(3));
		link = new LinkedList();
		link.addLast("444");
		assertEquals(1, link.size());
		assertEquals("444", link.get(0));
	}

	@Test
	public void testRemoveFirst() {
		Object obj = link.removeFirst();
		assertEquals("111", obj);
		assertEquals(2, link.size());
		assertEquals("222", link.get(0));
	}

	@Test
	public void testRemoveLast() {
		Object obj = link.removeLast();
		assertEquals(2, link.size());
		assertEquals("333", obj);
	}

	@Test
	public void testIterator() {
		Iterator it = link.iterator();
		assertEquals(true, it.hasNext());
		assertEquals("111", it.next());
		assertEquals("222", it.next());
		assertEquals("333", it.next());
		assertEquals(false, it.hasNext());
	}

}
