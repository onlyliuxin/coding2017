package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	LinkedList lkLst = null;
	@Before
	public void setUp() throws Exception {
		lkLst = new LinkedList();
		lkLst.add("ABC");
		lkLst.add("CDE");
		lkLst.add("EFG");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		lkLst.add("HIJ");
		System.out.println(lkLst.get(3));
		fail("Not yet implemented");
	}

	@Test
	public void testAddIntObject() {
		lkLst.add(1,"OPQ");
		System.out.println(lkLst.get(1));
		fail("Not yet implemented");
	}

	@Test
	public void testGet() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemove() {
		System.out.println(lkLst.size());
		System.out.println(lkLst.remove(2));
		System.out.println(lkLst.size());
		fail("Not yet implemented");
	}

	@Test
	public void testSize() {
		System.out.println(lkLst.size());
		lkLst.add("HIJ");
		lkLst.add("JKL");
		lkLst.add("LMN");
		System.out.println(lkLst.size());
		fail("Not yet implemented");
	}

	@Test
	public void testAddFirst() {		
		lkLst.addFirst("000");
		System.out.println(lkLst.get(0));
		fail("Not yet implemented");
	}

	@Test
	public void testAddLast() {
		System.out.println(lkLst.size());
		lkLst.addLast("XYZ");
		System.out.println(lkLst.size());
		System.out.println(lkLst.get(lkLst.size()-1));
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirst() {
		System.out.println(lkLst.get(0));
		System.out.println(lkLst.size());
		lkLst.removeFirst();
		System.out.println(lkLst.get(0));
		System.out.println(lkLst.size());
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveLast() {
		System.out.println("原last Node :"+lkLst.get(lkLst.size()-1));
		System.out.println("删除的 Node :"+lkLst.removeLast());
		System.out.println("最新的 Last Node："+lkLst.get(lkLst.size()-1));
		fail("Not yet implemented");
	}

	@Test
	public void testIterator() {
		fail("Not yet implemented");
	}

}
