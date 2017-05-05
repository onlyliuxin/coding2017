package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {
	private static LinkedList linkedList = new LinkedList();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddObject() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		System.out.println(linkedList);
	}

	@Test
	public void testAddIntObject() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		linkedList.add(2,"ddd");
		System.out.println(linkedList);
		System.out.println(linkedList.size());
	}

	@Test
	public void testGet() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		linkedList.add("eee");
		linkedList.add("fff");
		linkedList.add("ddd");
//		System.out.println(linkedList.size());
		System.out.println(linkedList.get(3));
	}

	@Test
	public void testRemove() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		linkedList.add("eee");
		linkedList.add("fff");
		linkedList.add("ddd");
		linkedList.remove(5);
		linkedList.remove(1);
		linkedList.remove(2);
		System.out.println(linkedList);
		System.out.println(linkedList.size());
	}

	@Test
	public void testSize() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		linkedList.add("eee");
		linkedList.add("fff");
		linkedList.add("ddd");
		System.out.println(linkedList.size());
	}

	@Test
	public void testAddFirst() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.addFirst("sss");
		System.out.println(linkedList);
	}

	@Test
	public void testAddLast() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		System.out.println(linkedList);
	}

	@Test
	public void testRemoveFirst() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		linkedList.removeFirst();
		linkedList.addFirst("eee");
		linkedList.removeFirst();
		System.out.println(linkedList);
	}

	@Test
	public void testRemoveLast() {
		linkedList.add("aaa");
		linkedList.add("bbb");
		linkedList.add("ccc");
		linkedList.removeLast();
		linkedList.add("eee");
		linkedList.addFirst("xxx");
		System.out.println(linkedList);
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
