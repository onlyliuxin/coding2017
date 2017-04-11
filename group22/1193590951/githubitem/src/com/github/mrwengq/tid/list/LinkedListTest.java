package com.github.mrwengq.tid.list;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	
	LinkedList ll  = null;

	@Before
	public void setUp() throws Exception {
		ll = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReverse() {
		ll.add(3);
		ll.add(7);
		ll.add(10);
		ll.add(6);
		ll.add(12);
		assertEquals((int)ll.get(0), 3);
		assertEquals((int)ll.get(1), 7);
		assertEquals((int)ll.get(2), 10);
		assertEquals((int)ll.get(3), 6);
		assertEquals((int)ll.get(4), 12);
		ll.reverse();
		assertEquals((int)ll.get(0), 12);
		assertEquals((int)ll.get(1), 6);
		assertEquals((int)ll.get(2), 10);
		assertEquals((int)ll.get(3), 7);
		assertEquals((int)ll.get(4), 3);
		
	}

	@Test
	public void testRemoveFirstHalf() {
		ll.add(2);
		ll.add(5);
		ll.add(7);
		ll.add(8);
		ll.add(10);
		assertEquals((int)ll.get(0), 2);
		assertEquals((int)ll.get(1), 5);
		assertEquals((int)ll.get(2), 7);
		assertEquals((int)ll.get(3), 8);
		assertEquals((int)ll.get(4), 10);
		assertEquals(ll.size(),5);
		ll.removeFirstHalf();
		assertEquals((int)ll.get(0), 7);
		assertEquals((int)ll.get(1), 8);
		assertEquals((int)ll.get(2), 10);
		assertEquals(ll.size(),3);
	}

	@Test
	public void testRemoveIntInt() {
		ll.add(2);
		ll.add(5);
		ll.add(7);
		ll.add(8);
		ll.add(10);
		assertEquals(ll.size(),5);
		ll.remove(1, 2);
		assertEquals((int)ll.get(0), 2);
		assertEquals((int)ll.get(1), 8);
		assertEquals((int)ll.get(2), 10);
		assertEquals(ll.size(),3);
	}

	@Test
	public void testGetElements() {
		LinkedList list = new LinkedList();
		list.add(1);
		list.add(3);
		list.add(4);
		list.add(6);
		assertEquals(list.size(),4);
		ll.add(11);
		ll.add(101);
		ll.add(201);
		ll.add(301);
		ll.add(401);
		ll.add(501);
		ll.add(601);
		ll.add(701);
		int [] b = ll.getElements(list);
		assertArrayEquals(new int[]{101,301,401,601}, b);
	}

	@Test
	public void testSubtract() {
		ll.add(11);
		ll.add(101);
		ll.add(201);
		ll.add(301);
		ll.add(401);
		ll.add(501);
		ll.add(601);
		ll.add(701);
		LinkedList list = new LinkedList();
		list.add(11);
		list.add(201);
		list.add(301);
		list.add(401);
		ll.subtract(list);
		assertEquals(list.size(),4);
		assertEquals((int)ll.get(0), 101);
		assertEquals((int)ll.get(1), 501);
		assertEquals((int)ll.get(2), 601);
		assertEquals((int)ll.get(3), 701);
	}

	@Test
	public void testRemoveDuplicateValues() {
		ll.add(11);
		ll.add(101);
		ll.add(101);
		ll.add(101);
		ll.add(401);
		ll.add(501);
		ll.add(501);
		ll.add(701);
		ll.removeDuplicateValues();
		assertEquals((int)ll.get(0), 11);
		assertEquals((int)ll.get(1), 101);
		assertEquals((int)ll.get(2), 401);
		assertEquals((int)ll.get(3), 501);
		assertEquals((int)ll.get(4), 701);

	}

	@Test
	public void testRemoveRange() {
		ll.add(11);
		ll.add(101);
		ll.add(201);
		ll.add(301);
		ll.add(401);
		ll.add(501);
		ll.add(601);
		ll.add(701);
		ll.removeRange(400, 700);
		assertEquals(ll.size(),5);
	
	}

	@Test
	public void testIntersection() {
		ll.add(11);
		ll.add(101);
		ll.add(201);

		LinkedList list = new LinkedList();
		list.add(101);
		list.add(121);
		list.add(300);
		ll = ll.intersection(list);

		assertEquals(ll.size(), 5);
		assertEquals((int)ll.get(0), 11);
		assertEquals((int)ll.get(1), 101);
		assertEquals((int)ll.get(2), 121);
		assertEquals((int)ll.get(3), 201);
		assertEquals((int)ll.get(4), 300);
		
	}

}
