package com.github.fei9009.coding2017.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class LinkedListTest {

private LinkedList aLinkedList;
	
	@Before
	public void setUpLinkedList() {
		aLinkedList = new LinkedList();
	}

	@Test
	public void testAddFirst() {
		aLinkedList.addFirst(5);
		assertEquals(5, aLinkedList.get(0));
		
		aLinkedList.addFirst(6);
		assertEquals(6, aLinkedList.get(0));
		assertEquals(5, aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
	}
	
	@Test
	public void testAddLast() {
		aLinkedList.addLast("hello");
		assertEquals("hello", aLinkedList.get(0));
		
		aLinkedList.addLast("world");
		assertEquals("hello", aLinkedList.get(0));
		assertEquals("world", aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
	}
	
	@Test
	public void testRemoveFirst() {
		aLinkedList.addLast("hello");
		aLinkedList.addLast("world");
		
		aLinkedList.removeFirst();
		assertEquals("world", aLinkedList.get(0));
		assertEquals(1, aLinkedList.size());
		
		aLinkedList.removeFirst();
		assertEquals(0, aLinkedList.size());
	}
	
	@Test
	public void testRemoveLast() {
		aLinkedList.addFirst("world");
		aLinkedList.addFirst("hello");
		
		aLinkedList.removeLast();
		assertEquals("hello", aLinkedList.get(0));
		assertEquals(1, aLinkedList.size());
		
		aLinkedList.removeLast();
		assertEquals(0, aLinkedList.size());
	}
	
	@Test
	public void testLinkedListFunctional() {
		for (int i=1; i<4; i++) {
			aLinkedList.add(i);  // [1,2,3]
		}
		aLinkedList.remove(1);  // [1,3]
		
		aLinkedList.add(1, 0);  // [1,0,3]
		for (int i=4; i<6; i++) {
			aLinkedList.addFirst(i);  // [5, 4, 1, 0, 3]
		}
		assertEquals(5, aLinkedList.size());
		assertEquals(5, aLinkedList.get(0));
		assertEquals(1, aLinkedList.get(2));
		assertEquals(0, aLinkedList.get(3));
		
		aLinkedList.remove(3);  // [5, 4, 1, 3]
		assertEquals(3, aLinkedList.get(aLinkedList.size()-1));
		aLinkedList.removeLast();  // [5, 4, 1]
		assertEquals(1, aLinkedList.get(aLinkedList.size()-1));
		aLinkedList.removeFirst();   // [4,1]
		
		assertEquals(4, aLinkedList.get(0));
		assertEquals(1, aLinkedList.get(1));
		assertEquals(2, aLinkedList.size());
	}

}
