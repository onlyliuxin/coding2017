package com.github.mrwengq.first;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddObject() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");

		
	}

	@Test
	public void testAddIntObject() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		li.add(3, "6");
		assertEquals(li.get(0), "1");
		assertEquals(li.get(1), "2");
		assertEquals(li.get(2), "3");
		assertEquals(li.get(3), "6");
		assertEquals(li.get(4), "4");
		assertEquals(li.get(5), "5");

	}

	@Test
	public void testGet() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		assertEquals(li.get(0), "1");
		assertEquals(li.get(1), "2");
		assertEquals(li.get(2), "3");
		assertEquals(li.get(3), "4");
		assertEquals(li.get(4), "5");


	}

	@Test
	public void testRemoveInt() {
		/*LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		li.remove(3);
		assertEquals(li.get(0), "1");
		assertEquals(li.get(1), "2");
		assertEquals(li.get(2), "3");
		assertEquals(li.get(3), "5");*/
		LinkedList ll = new LinkedList();
		ll.add(11);
		ll.add(101);
		ll.add(201);
		ll.add(301);
		ll.add(401);
		ll.add(501);
		ll.add(601);
		ll.add(701);
		ll.remove(0);
		ll.remove(1);
		//ll.remove(3);
		//ll.remove(4);
		System.out.println(ll.get(0)+" "+ll.get(1)+" "+ll.get(2)+" "+ll.get(3));

		
	}

	@Test
	public void testSize() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		assertEquals(li.size(), 5);
	}

	@Test
	public void testAddFirst() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		li.addFirst("6");
		assertEquals(li.get(0), "6");
		assertEquals(li.get(1), "1");
		assertEquals(li.get(2), "2");
		assertEquals(li.get(3), "3");
		assertEquals(li.get(4), "4");
		assertEquals(li.get(5), "5");
	}

	@Test
	public void testAddLast() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		li.addLast("6");
		assertEquals(li.get(0), "1");
		assertEquals(li.get(1), "2");
		assertEquals(li.get(2), "3");
		assertEquals(li.get(3), "4");
		assertEquals(li.get(4), "5");
		assertEquals(li.get(5), "6");
	}

	@Test
	public void testRemoveFirst() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		li.removeFirst();
		assertEquals(li.get(0), "2");
		assertEquals(li.get(1), "3");
		assertEquals(li.get(2), "4");
		assertEquals(li.get(3), "5");
	}

	@Test
	public void testRemoveLast() {
		LinkedList li = new LinkedList();
		li.add("1");
		li.add("2");
		li.add("3");
		li.add("4");
		li.add("5");
		li.removeLast();
		assertEquals(li.get(0), "1");
		assertEquals(li.get(1), "2");
		assertEquals(li.get(2), "3");
		assertEquals(li.get(3), "4");
	}

	@Test
	public void testIterator() {
		LinkedList list = new LinkedList();
		list.add("1");
		list.add("2");
		list.add("3");
		list.add("4");
		list.add("5");
		Iterator iter = list.iterator();
		int i = 0;
		String [] o = new String[] { "1","2", "3","4", "5"};
 
		while(iter.hasNext()){
			
			assertEquals(iter.next(),o[i]);
			i++;
		};
	}

	@Test
	public void testReverse() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveFirstHalf() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetElements() {
		fail("Not yet implemented");
	}

	@Test
	public void testSubtract() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveDuplicateValues() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveRange() {
		fail("Not yet implemented");
	}

	@Test
	public void testIntersection() {
		fail("Not yet implemented");
	}

}
