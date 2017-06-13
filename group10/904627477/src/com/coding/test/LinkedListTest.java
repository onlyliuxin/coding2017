package com.coding.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.coding.basic.Iterator;
import com.coding.basic.LinkedList;

public class LinkedListTest {
	
	private static LinkedList link;

	@Before
	public void setUp() throws Exception {
		link = new LinkedList();
		link.add("111");
		link.add("222");
		link.add("333");
	}

	@After
	public void tearDown() throws Exception {
		link = null;
	}

	@Test
	public void testAddObject() {
		link.add("444");
		assertEquals(4, link.size());
		assertEquals("444", link.get(3));
		link = new LinkedList();
		link.add("000");
		assertEquals(1, link.size());
		assertEquals("000", link.get(0));
	}

	@Test
	public void testAddIntObject() {
		link.add(2,"444");
		assertEquals(4, link.size());
		assertEquals("444", link.get(2));
		assertEquals("333", link.get(3));
		assertEquals("222", link.get(1));
		link = new LinkedList();
		link.add(0,"000");
		assertEquals(1, link.size());
		assertEquals("000", link.get(0));
	}

	@Test
	public void testGet() {
		assertEquals("222", link.get(1));
	}

	@Test
	public void testRemove() {
		Object obj = link.remove(1);
		assertEquals("222", obj);
		assertEquals("333", link.get(1));
		assertEquals(2, link.size());
	}

	@Test
	public void testSize() {
		assertEquals(3, link.size());
	}

	@Test
	public void testAddFirst() {
		link.addFirst("000");
		assertEquals(4, link.size());
		assertEquals("000", link.get(0));
		link = new LinkedList();
		link.addFirst("000");
		assertEquals(1, link.size());
		assertEquals("000", link.get(0));
	}

	@Test
	public void testAddLast() {
		link.addLast("444");
		assertEquals(4, link.size());
		assertEquals("444", link.get(3));
		link = new LinkedList();
		link.addLast("444");
		assertEquals(1, link.size());
		assertEquals("444", link.get(0));
	}

	@Test
	public void testRemoveFirst() {
		Object obj = link.removeFirst();
		assertEquals("111", obj);
		assertEquals(2, link.size());
		assertEquals("222", link.get(0));
	}

	@Test
	public void testRemoveLast() {
		Object obj = link.removeLast();
		assertEquals(2, link.size());
		assertEquals("333", obj);
	}

	@Test
	public void testIterator() {
		Iterator it = link.iterator();
		assertEquals(true, it.hasNext());
		assertEquals("111", it.next());
		assertEquals("222", it.next());
		assertEquals("333", it.next());
		assertEquals(false, it.hasNext());
	}
	
	@Test
	public void testReverse(){
		link.reverse();
		assertEquals(3, link.size());
		assertEquals("333", link.get(0));
		assertEquals("222", link.get(1));
		assertEquals("111", link.get(2));
	}
	
	@Test
	public void testRemoveFirstHalf(){
		link.add("444");
		link.add("555");
		link.removeFirstHalf();
		assertEquals(3, link.size());
		assertEquals("333", link.get(0));
		link.add("666");
		link.removeFirstHalf();
		assertEquals(2, link.size());
		assertEquals("555", link.get(0));
	}
	
	@Test
	public void testRemoveForIndex(){
		link.add("444");
		link.add("555");
		link.remove(1, 2);
		assertEquals(3, link.size());
		assertEquals("444", link.get(1));
		
		link.remove(2, 5);
		assertEquals(2, link.size());
		
		link.remove(0,1);
		assertEquals(1, link.size());
		assertEquals("444", link.get(0));
	}
	
	@Test
	//11->101->201->301->401->501->601->701 listB = 1->3->4->6  [101,301,401,601]
	public void testGetElements(){
		link = new LinkedList();
		link.add(11);
		link.add(101);
		link.add(201);
		link.add(301);
		link.add(401);
		link.add(501);
		link.add(601);
		link.add(701);
		LinkedList linkB = new LinkedList();
		linkB.add(1);
		linkB.add(3);
		linkB.add(4);
		linkB.add(6);
		int[] eles = link.getElements(linkB);
		assertEquals(4, eles.length);
		assertEquals(101, eles[0]);
		assertEquals(301, eles[1]);
		assertEquals(401, eles[2]);
		assertEquals(601, eles[3]);
	}
	
	@Test
	public void testSubtract(){
		link = new LinkedList();
		link.add(11);
		link.add(101);
		link.add(201);
		link.add(301);
		link.add(401);
		link.add(501);
		link.add(601);
		link.add(701);
		LinkedList linkB = new LinkedList();
		linkB.add(401);
		linkB.add(201);
		linkB.add(601);
		link.subtract(linkB);
		assertEquals(5, link.size());
		assertEquals(11, link.get(0));
		assertEquals(101, link.get(1));
		assertEquals(301, link.get(2));
		assertEquals(501, link.get(3));
		assertEquals(701, link.get(4));
	}

	@Test
	public void testRemoveDuplicateValues(){
		link = new LinkedList();
		link.add(11);
		link.add(101);
		link.add(101);
		link.add(301);
		link.add(401);
		link.add(401);
		link.removeDuplicateValues();
		assertEquals(4, link.size());
		assertEquals(11, link.get(0));
		assertEquals(101, link.get(1));
		assertEquals(301, link.get(2));
		assertEquals(401, link.get(3));
	}
	
	@Test
	public void testRemoveRange(){
		link = new LinkedList();
		link.add(11);
		link.add(101);
		link.add(201);
		link.add(301);
		link.add(401);
		link.add(501);
		link.add(601);
		link.add(701);
		link.removeRange(200, 600);
		assertEquals(4, link.size());
		assertEquals(11, link.get(0));
		assertEquals(101, link.get(1));
		assertEquals(601, link.get(2));
		assertEquals(701, link.get(3));
	}
	
	@Test
	public void testIntersection(){
		link = new LinkedList();
		link.add(11);
		link.add(101);
		link.add(201);
		link.add(301);
		link.add(401);
		link.add(501);
		link.add(601);
		link.add(701);
		LinkedList linkB = new LinkedList();
		linkB.add(201);
		linkB.add(305);
		linkB.add(401);
		linkB.add(504);
		linkB.add(601);
		LinkedList linkC = link.intersection(linkB);
		assertEquals(3, linkC.size());
		assertEquals(201, linkC.get(0));
		assertEquals(401, linkC.get(1));
		assertEquals(601, linkC.get(2));
	}
	
}
