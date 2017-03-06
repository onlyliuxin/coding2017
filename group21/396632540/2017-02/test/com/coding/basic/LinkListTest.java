package com.coding.basic;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.coding.basic.list.Iterator;
import com.coding.basic.list.LinkedList;

public class LinkListTest {

	private LinkedList list = new LinkedList();
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		list.add("setUp");
		testAddFirst();
		list.add(1, "after setUp run testAddFirst()");
		testIterator();
		System.out.println("---------setUp End---------");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {		
		list.add("testAddObject");
		testIterator();
		testSize();
		testGet();
	}
	
	@Test
	public void testIterator() {
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	@Test
	public void testAddIntObject() {
		for (int u = 0; u < 4; u++) {
			list.add(u, "testAddIntObject"+Integer.toString(u));
		}
		testIterator();
	}

	@Test
	public void testGet() {
		System.out.println("testGet index==1,data=="+list.get(1));
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		System.out.println("size ==="+list.size());
	}

	@Test
	public void testAddFirst() {
		list.addFirst("testAddFirst");
	}

	@Test
	public void testAddLast() {
		list.addLast("testAddLast");
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
