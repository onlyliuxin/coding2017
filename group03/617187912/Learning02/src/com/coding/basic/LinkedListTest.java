package com.coding.basic;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import junit.framework.AssertionFailedError;

public class LinkedListTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testReverse() {
		LinkedList list1 = createLinkList(0, 1000);
		list1.reverse();
		list1.reverse();
		LinkedList list2 = createLinkList(0, 1000);
		compareEquals(list1, list2);
	}

	@Test
	public void testRemoveFirstHalf() {
		LinkedList list1 = createLinkList(0, 5);
		list1.removeFirstHalf();
		LinkedList list2 = createLinkList(2, 5);

		compareEquals(list1, list2);

		LinkedList list3 = createLinkList(0, 6);
		list3.removeFirstHalf();
		LinkedList list4 = createLinkList(3, 6);
		compareEquals(list3, list4);
	}

	@Test
	public void testRemoveIntInt() {
		LinkedList list1 = createLinkList(0, 10);
		list1.remove(3, 5);
		LinkedList list2 = createLinkList(0, 3);
		addDataToLinkList(list2, 8, 10);
		compareEquals(list2, list1);
	}

	@Test
	public void testGetElements() {
		LinkedList list1 = createLinkList(0, 10);
		LinkedList list2 = createLinkList(3, 8);
		int[] arrResult = { 3, 4, 5, 6, 7 };
		assertArrayEquals(arrResult, list1.getElements(list2));

		// LinkedList list3 = createLinkList(0, 10);
		// LinkedList list4 = createLinkList(9, 11);
		// list3.getElements(list4);
	}

	@Test
	public void testSubtract() {
		LinkedList list1 = createLinkList(0, 10);
		LinkedList list2 = createLinkList(3, 8);
		list1.subtract(list2);
		LinkedList list3 = createLinkList(0, 3);
		addDataToLinkList(list3, 8, 10);
		compareEquals(list3, list1);
	}

	@Test
	public void testRemoveDuplicateValues() {
		LinkedList list1 = createLinkList(0, 2);
		list1.add(1);
		list1.add(1);
		addDataToLinkList(list1, 2, 5);
		list1.add(4);
		list1.removeDuplicateValues();
		LinkedList list2 = createLinkList(0, 5);
		compareEquals(list1, list2);
	}

	@Test
	public void testRemoveRange() {
		LinkedList list1 = createLinkList(0, 10);
		list1.removeRange(0, 5);
		LinkedList list2 = createLinkList(5, 10);
		compareEquals(list2, list1);

		LinkedList list3 = createLinkList(0, 6);
		list3.removeRange(3, 5);
		LinkedList list4 = createLinkList(0, 3);
		compareEquals(list4, list3);

		LinkedList list5 = createLinkList(0, 6);
		list5.removeRange(0, 7);
		LinkedList list6 = new LinkedList();
		compareEquals(list6, list5);
		
		LinkedList list7 = createLinkList(0, 100000);
		list7.removeRange(0, 50000);
		LinkedList list8 = createLinkList(50000, 100000);
		//compareEquals(list8, list7);
	}

	@Test
	public void testIntersection() {
		LinkedList list1 = new LinkedList();
		LinkedList list2 = new LinkedList();
		for (int i = 0; i < 10; i++) {
			list1.add(i);
		}
		for (int i = 3; i < 12; i++) {
			list2.add(i);
		}
		LinkedList list3 = new LinkedList();
		for (int i = 3; i < 10; i++) {
			list3.add(i);
		}
		LinkedList list4 = list1.interSection(list2);
		compareEquals(list3, list4);
	}

	private void compareEquals(LinkedList list1, LinkedList list2) {
		assertEquals(list1.size(), list2.size());
		for (int i = 0; i < list1.size(); i++) {
			assertEquals(list1.get(i), list2.get(i));
		}
	}

	private void printLinkedList(LinkedList list) {
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	private LinkedList createLinkList(int min, int max) {
		LinkedList list = new LinkedList();
		for (int i = min; i < max; i++) {
			list.add(i);
		}
		return list;
	}

	private void addDataToLinkList(LinkedList list, int min, int max) {
		for (int i = min; i < max; i++) {
			list.add(i);
		}
	}
}
