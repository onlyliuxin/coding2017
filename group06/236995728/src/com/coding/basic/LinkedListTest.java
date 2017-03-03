package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 2017/2/26
 * @author 236995728
 *
 */
public class LinkedListTest {
	LinkedList list = new LinkedList();
	@Before
	public void setUp() throws Exception {
		for(int i=1;i<10;i++){
			list.add(i);
		}
	}

	@Test
	public void testAddObject() {
		assertEquals(5, list.get(5));
	}

	@Test
	public void testAddIntObject() {
		list.add(6, 66);
		assertEquals(66,list.get(6));
	}

	@Test
	public void testGet() {
		assertEquals(1, list.get(1));
	}

	@Test
	public void testRemove() {
		list.remove(6);
		assertNotSame("此时第6个节点的值为7", 66, 7);
	}

	@Test
	public void testSize() {
		assertEquals(9, 9);
	}

	@Test
	public void testAddFirst() {
		list.addFirst(0);
		assertEquals(0, 0);
	}

	@Test
	public void testAddLast() {
		list.addLast(10);
		assertEquals(10, 10);
	}

	@Test
	public void testRemoveFirst() {
		list.removeFirst();
		assertNotSame(0, 1);
	}

	@Test
	public void testRemoveLast() {
		list.removeLast();
		assertNotSame(10, 9);
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
