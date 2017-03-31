package com.github.ipk2015.coding2017.basic.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.github.ipk2015.coding2017.basic.LinkedList;

public class LinkedListTest {
	LinkedList list;
	@Before
	public void setUp() throws Exception {
		list=new LinkedList(); 
	}

	@Test
	public void testAddObject() {
		list.add("hehe1");
		list.add("hehe2");
		assertEquals("hehe2", list.get(1));
	}

	@Test
	public void testAddIntObject() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.add(1,"arm");
		assertEquals("arm", list.get(1));
	}

	@Test
	public void testGet() {
		list.add("hehe1");
		list.add("hehe2");
		assertEquals("hehe2", list.get(1));
	}

	@Test
	public void testRemoveInt() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.remove(1);
		assertEquals(2, list.size());
	}

	@Test
	public void testSize() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		assertEquals(3, list.size());
	}

	@Test
	public void testAddFirst() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.addFirst("arm");
		assertEquals("arm", list.get(0));
	}

	@Test
	public void testAddLast() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.addLast("arm");
		assertEquals("arm", list.get(list.size()-1));
	}

	@Test
	public void testRemoveFirst() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.removeFirst();
		assertEquals("hehe2", list.get(0));
	}
	@Test
	public void testRemoveLast() {
		list.add("hehe1");
		list.add("hehe2");
		list.add("hehe3");
		list.removeLast();
		assertEquals("hehe2", list.get(list.size()-1));
	}

}
