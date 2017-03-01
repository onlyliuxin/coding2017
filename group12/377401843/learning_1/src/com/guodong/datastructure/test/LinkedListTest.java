package com.guodong.datastructure.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.guodong.datastructure.Iterator;
import com.guodong.datastructure.LinkedList;

public class LinkedListTest {

	private LinkedList linkedList;

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		linkedList.add(1);
		assertEquals(1, linkedList.size());
		assertEquals(1, linkedList.get(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForAdd1() {
		linkedList.add(-1, 1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForAdd2() {
		linkedList.add(1, 1);
	}

	@Test
	public void testAddIntObject() {
		linkedList.add(0, 1);
		linkedList.add(1, 2);
		assertEquals(1, linkedList.get(0));
		
		linkedList.add(1,3);
		assertEquals(2, linkedList.get(2));
		assertEquals(3, linkedList.get(1));
		assertEquals(3, linkedList.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForGet1() {
		linkedList.get(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForGet2() {
		linkedList.get(0);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
	public void testExceptionForGet3() {
		linkedList.get(1);
	}
	
	@Test
	public void testGet() {
		linkedList.add(0, 1);
		linkedList.add(1, 2);
		assertEquals(2, linkedList.get(1));
	}

	@Test
	public void testGetLast() {
		linkedList.add(1);
		assertEquals(1, linkedList.getLast());
		
		linkedList.add(2);
		assertEquals(2, linkedList.getLast());
	}

	@Test
	public void testRemove() {
		linkedList.add(1);
		assertEquals(1, linkedList.remove(0));
		assertEquals(0, linkedList.size());
	}

	@Test
	public void testSize() {
		linkedList.add(1);
		linkedList.add(1);
		linkedList.add(1);
		assertEquals(3, linkedList.size());
	}

	@Test
	public void testAddFirst() {
		linkedList.addFirst(1);
		assertEquals(1, linkedList.get(0));
		
		linkedList.addFirst(2);
		linkedList.addFirst(3);
		assertEquals(3, linkedList.get(0));
		assertEquals(1, linkedList.getLast());
	}

	@Test
	public void testAddLast() {
		linkedList.addLast(1);
		assertEquals(1, linkedList.getLast());
		assertEquals(1, linkedList.get(0));
	}

	@Test
	public void testRemoveFirst() {
		linkedList.addFirst(1);
		assertEquals(1, linkedList.removeFirst());
		assertEquals(0, linkedList.size());
	}

	@Test
	public void testRemoveLast() {
		linkedList.addLast(2);
		assertEquals(2, linkedList.removeLast());
		assertEquals(0, linkedList.size());
	}

	@Test
	public void testIterator() {
		Iterator iterator = linkedList.iterator();
		assertFalse(iterator.hasNext());
		
		linkedList.add(1);
		assertTrue(iterator.hasNext());
		assertEquals(1, iterator.next());
		assertFalse(iterator.hasNext());
	}

}
