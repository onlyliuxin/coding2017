package com.coding.basic;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testAddObject() {
		LinkedList list = new LinkedList();
		list.add(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.add(4);
		list.add(3);
		list.add(2);
		assertEquals(4, list.size());
		assertEquals(4, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(2, list.get(3));
	}

	@Test
	public void testAddIntObject() {
		LinkedList list = new LinkedList();
		list.add(0, 5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.add(4);
		list.add(3);
		list.add(2);
		
		list.add(1, 1);
		assertEquals(1, list.get(1));
	}

	@Test
	public void testRemove() {
		LinkedList list = new LinkedList();
		assertEquals(null, list.remove(0));
		list.add(4);
		assertEquals(4, list.remove(0));
		
		list.add(5);
		list.add(-1);
		list.add(16);
		list.add(2);
		list.add(7);
		assertEquals(16, list.remove(2));
		assertEquals(4, list.size());
		assertEquals(2, list.get(2));
	}

	@Test
	public void testAddFirst() {
		LinkedList list = new LinkedList();
		list.addFirst(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.addFirst(4);
		list.addFirst(3);
		list.addFirst(2);
		assertEquals(4, list.size());
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(4, list.get(3));
	}

	@Test
	public void testAddLast() {
		LinkedList list = new LinkedList();
		list.addLast(5);
		assertEquals(1, list.size());
		assertEquals(5, list.get(0));
		
		list.addLast(4);
		list.addLast(3);
		list.addLast(2);
		assertEquals(4, list.size());
		assertEquals(4, list.get(1));
		assertEquals(3, list.get(2));
		assertEquals(2, list.get(3));
	}

	@Test
	public void testRemoveFirst() {
		LinkedList list = new LinkedList();
		assertEquals(null, list.removeFirst());
		
		list.add(4);
		list.add(3);
		list.add(2);
		assertEquals(4, list.removeFirst());
		assertEquals(3, list.removeFirst());
		assertEquals(2, list.removeFirst());
	}

	@Test
	public void testRemoveLast() {
		LinkedList list = new LinkedList();
		assertEquals(null, list.removeLast());
		
		list.add(4);
		list.add(3);
		list.add(2);
		assertEquals(2, list.removeLast());
		assertEquals(3, list.removeLast());
	}

	@Test
	public void testIterator() {
		LinkedList list = new LinkedList();
		list.add(4);
		list.add(3);
		list.add(2);
		
		Iterator it = list.iterator();
		
		assertEquals(4, it.next());
		assertEquals(3, it.next());
		assertEquals(2, it.next());
	}
	
	@Test
	public void testReverse() {
		LinkedList list = new LinkedList();
		list.add(3);
		list.add(7);
		list.add(10);
		list.add(15);
		
		list.reverse();
		assertEquals(15, list.get(0));
		
		LinkedList list2 = new LinkedList();
		list2.reverse();
	}
	
	@Test
	public void testRemoveFirstHalf() {
		LinkedList list = new LinkedList();
		list.add(2);
		list.add(5);
		list.add(7);
		list.add(8);
		list.add(10);
	
		list.removeFirstHalf();
	}
	
	
	@Test
	public void testRemoveDuplicateValues() {
		LinkedList list = new LinkedList();
		list.add(1);

		// with single element
		assertEquals(1, list.get(0));
		
		// add another one with 1->1
		list.add(1);
		list.removeDuplicateValues();
		assertEquals(1, list.get(0));
		assertEquals(1, list.size());
		
		// test the duplicate value is the last one
		for(int i=2; i<6; i++) {
			list.add(i);
		}
		list.add(5);
		list.removeDuplicateValues();
		assertEquals(5, list.get(4));
		assertEquals(5, list.size());
		
		// regular
		for(int i=0; i<6; i++) {
			list.add(5);
		}
		list.add(6);

		list.removeDuplicateValues();
		assertEquals(6, list.get(5));
		assertEquals(6, list.size());
	}
	
	@Test
	public void testRemoveRange() {
		LinkedList list = new LinkedList();

		// regular
		for(int i=1; i<7; i++) {
			list.add(i);
		}
		
		list.removeRange(2, 4);
		assertEquals(5, list.size());
		
		// head case
		list.add(1, 1);
		list.removeRange(0, 3);
		assertEquals(3, list.size());
		
		// tail case
		list.add(6);
		list.add(7);
		list.removeRange(5, 20);
		assertEquals(2, list.size());
	}
	
	@Test
	public void testRemoveLength() {
		LinkedList list = new LinkedList();

		// regular
		for(int i=0; i<9; i++) {
			list.add(i);
		}
		
		// regular
		list.remove(4, 2);
		
		// head
		LinkedList list2 = new LinkedList();
		for(int i=0; i<9; i++) {
			list2.add(i);
		}
		list2.remove(0, 3);
		
		// tail
		LinkedList list3 = new LinkedList();
		for(int i=0; i<10; i++) {
			list3.add(i);
		}
		list3.remove(9, 3);
		assertEquals(9, list3.size());
	}
	
	@Test
	public void testGetElements() {
		LinkedList list1 = new LinkedList();
		// 11->101->201->301->401->501->601->701
		list1.add(11);
		list1.add(101);
		list1.add(201);
		list1.add(301);
		list1.add(401);
		list1.add(501);
		list1.add(601);
		list1.add(701);
		
		LinkedList list2 = new LinkedList();
		// 1->3->4->6
		list2.add(1);
		list2.add(3);
		list2.add(4);
		list2.add(6);
		
		int[] newArr = list1.getElements(list2);
		assertArrayEquals(new int[]{101,301,401,601}, newArr);
	}
	
	@Test
	public void testSubtract() {
		LinkedList list1 = new LinkedList();
		// 11->101->201->301->401->501->601->701
		list1.add(11);
		list1.add(101);
		list1.add(201);
		list1.add(301);
		list1.add(401);
		list1.add(501);
		list1.add(601);
		list1.add(701);
		
		LinkedList list2 = new LinkedList();
		// 1->3->4->6
		list2.add(1);
		list2.add(3);
		list2.add(4);
		list2.add(6);
		
		list1.subtract(list2);
		// 11->201->501->701
		assertEquals(4, list1.size());
	}
	
	@Test
	public void testIntersection() {
		LinkedList list1 = new LinkedList();
		list1.add(1);
		list1.add(2);
		list1.add(4);
		list1.add(5);
		
		LinkedList list2 = new LinkedList();
		list2.add(2);
		list2.add(3);
		list2.add(4);
		list2.add(7);
		
		LinkedList l3 = list1.intersection(list2);
		assertEquals(2, l3.size());
	}
}
