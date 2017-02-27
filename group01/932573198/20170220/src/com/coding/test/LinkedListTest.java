package com.coding.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {
	
	private LinkedList list;

	@Before
	public void setUp() throws Exception {
		list = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("size:" + list.size());
		System.out.println(list);
	}

	@Test
	public void testAddObject() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
	}

	@Test
	public void testAddIntObject() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(0, 9);
	}

	@Test
	public void testGet() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println(list.get(4));
	}

	@Test
	public void testRemove() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println(list.remove(0));
	}

	@Test
	public void testSize() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println(list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst(7);
	}

	@Test
	public void testAddLast() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.addLast(1);
	}

	@Test
	public void testRemoveFirst() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.removeFirst();
	}

	@Test
	public void testRemoveLast() {
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		System.out.println(list.removeLast());
	}

}
