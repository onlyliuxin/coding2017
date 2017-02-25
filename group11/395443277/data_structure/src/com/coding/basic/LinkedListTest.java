package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testAddObject() {
		LinkedList list = new LinkedList();
		list.add(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.add(4);
		list.add(3);
		list.add(2);
		assertEquals(4, list.size());
		assertEquals(4, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(2, list.get(3));
	}

	@Test
	public void testAddIntObject() {
		LinkedList list = new LinkedList();
		list.add(0, 5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.add(4);
		list.add(3);
		list.add(2);
		
		list.add(1, 1);
		assertEquals(1, list.get(1));
	}

	@Test
	public void testRemove() {
		LinkedList list = new LinkedList();
		assertEquals(null, list.remove(0));
		list.add(4);
		assertEquals(4, list.remove(0));
		
		list.add(5);
		list.add(-1);
		list.add(16);
		list.add(2);
		list.add(7);
		assertEquals(16, list.remove(2));
		assertEquals(4, list.size());
		assertEquals(2, list.get(2));
	}

	@Test
	public void testAddFirst() {
		LinkedList list = new LinkedList();
		list.addFirst(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.addFirst(4);
		list.addFirst(3);
		list.addFirst(2);
		assertEquals(4, list.size());
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
	}

	@Test
	public void testAddLast() {
		LinkedList list = new LinkedList();
		list.addLast(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.addLast(4);
		list.addLast(3);
		list.addLast(2);
		assertEquals(4, list.size());
		assertEquals(4, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(2, list.get(3));
	}

	@Test
	public void testRemoveFirst() {
		LinkedList list = new LinkedList();
		assertEquals(null, list.removeFirst());
		
		list.add(4);
		list.add(3);
		list.add(2);
		assertEquals(4, list.removeFirst());
		assertEquals(3, list.removeFirst());
		assertEquals(2, list.removeFirst());
	}

	@Test
	public void testRemoveLast() {
		LinkedList list = new LinkedList();
		assertEquals(null, list.removeLast());
		
		list.add(4);
		list.add(3);
		list.add(2);
		assertEquals(2, list.removeLast());
		assertEquals(3, list.removeLast());
	}

	@Test
	public void testIterator() {
		LinkedList list = new LinkedList();
		list.add(4);
		list.add(3);
		list.add(2);
		
		Iterator it = list.iterator();
		
		assertEquals(4, it.next());
		assertEquals(3, it.next());
		assertEquals(2, it.next());
	}
}
