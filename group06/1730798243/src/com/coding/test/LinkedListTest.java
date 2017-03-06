package com.coding.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.coding.basic.first.List;
import com.coding.basic.first.impl.LinkedList;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddObject() {
		List  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		assertEquals(1, list.get(2));
	}

	@Test
	public void testAddIntObject() {
		List  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		assertEquals(8, list.get(3));
	}

	@Test
	public void testGet() {
		List  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		assertEquals(3, list.get(6));
	}

	@Test
	public void testRemove() {
		List  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		list.remove(3);
		assertEquals(2, list.get(3));
	}

	@Test
	public void testSize() {
		List  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		assertEquals(7, list.size());
	}

	@Test
	public void testAddLast() {
		LinkedList  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		list.addLast(7);
		assertEquals(7, list.get(7));
	}

	@Test
	public void testRemoveFirst() {
		LinkedList  list = new LinkedList();
		list.add(0,4);
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(0,5);
		list.add(3,8);
		list.add(5,6);//5418263
		list.removeFirst();
		assertEquals(4, list.get(0));
		assertEquals(6, list.size());
	}

}
