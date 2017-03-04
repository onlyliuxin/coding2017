package com.coding.basic.tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.LinkedList;

public class LinkedListTest {
	
	LinkedList linkedList;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		linkedList = new LinkedList();
	}

	@After
	public void tearDown() throws Exception {
		linkedList = null;
	}

	@Test
	public void testAddObject() {
		linkedList.add(new Integer(0));
		assertEquals(linkedList.size(), 1);
	}

	@Test
	public void testAddIntObject() {
		//fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		//fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {
		//fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		//fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		//fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		//fail("Not yet implemented");
	}

}
