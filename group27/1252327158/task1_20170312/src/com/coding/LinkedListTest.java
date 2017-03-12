package com.coding;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	LinkedList<String> list; 

	@Before
	public void setUp() throws Exception {
		list = new LinkedList<String>();
		list.add("first");
		list.add("second");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddT() {
		list.add("third");
		Assert.assertEquals("second", list.get(1));
		Assert.assertEquals("first", list.get(2));
	}

	@Test
	public void testAddIntT() {
		list.add(1,"third");
		Assert.assertEquals("third", list.get(1));
		Assert.assertEquals("first", list.get(2));
	}

	@Test
	public void testGet() {
		Assert.assertEquals("second", list.get(0));
	}

	@Test
	public void testRemoveInt() {
		list.remove(0);
		list.add("third");
		list.add("forth");
		list.remove(1);
		Assert.assertEquals("forth", list.get(0));
		Assert.assertEquals("first", list.get(1));
	}

	@Test
	public void testSize() {
		list.remove(0);
		list.add("third");
		list.add("forth");
		list.remove(1);
		Assert.assertEquals(2, list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst("third");
		Assert.assertEquals("third", list.get(0));
		Assert.assertEquals("second", list.get(1));
	}

	@Test
	public void testAddLast() {
		list.addLast("third");
		Assert.assertEquals("third", list.get(2));
	}

	@Test
	public void testRemoveFirst() {
		list.removeFirst();
		Assert.assertEquals("first", list.get(0));
	}

	@Test
	public void testRemoveLast() {
		list.removeLast();
		Assert.assertEquals(1, list.size());
	}

	@Test
	public void testIterator() {
		Iterator<String> iter = list.iterator();
		if (iter.hasNext()) {
			Assert.assertEquals("second", iter.next());
		}
	}

}
