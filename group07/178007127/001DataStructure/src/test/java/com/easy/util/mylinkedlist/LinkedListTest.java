package com.easy.util.mylinkedlist;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import com.easy.util.mylinkedlist.LinkedList;

public class LinkedListTest {

	@Test
	public void test_add_object() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		assertEquals("[aa,bb]", list.toString());
	}

	@Test
	public void test_add_int_object() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add(1,"aabb");
		assertEquals("[aa,aabb,bb]", list.toString());
	}

	@Test
	public void testGet() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add(1,"aabb");
		assertEquals("aa", list.get(0));
		assertEquals("aabb", list.get(1));
		assertEquals("bb", list.get(2));
	}

	@Test
	public void testRemove() {
		LinkedList list=new LinkedList();
		list.add("aa");
		list.add("bb");
		list.add("cc");
		boolean b = list.remove("bb");
		assertEquals(true, b);
		assertEquals("[aa,cc]", list.toString());
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		fail("Not yet implemented");
	}

}
